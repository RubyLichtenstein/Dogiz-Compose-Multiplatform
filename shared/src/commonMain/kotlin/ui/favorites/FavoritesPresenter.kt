package ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import domain.images.DogImage
import kotlinx.coroutines.flow.Flow
import utils.common.UiState
import utils.common.asUiState
import utils.common.mapSuccess

data class ChipInfo(
    val label: String,
    val selected: Boolean,
)

data class FavoritesModel(
    val dogImages: List<DogImage>,
    val filterChipsInfo: Set<ChipInfo>,
)

sealed interface Event {
    data class ToggleSelectedBreed(val breed: ChipInfo) : Event
}

@Composable
fun FavoritesPresenter(
    events: Flow<Event>,
    favoriteImagesFlow: Flow<List<DogImage>>
): UiState<FavoritesModel> {
    var favoriteImagesResult by remember { mutableStateOf<UiState<Collection<DogImage>>>(UiState.Loading) }
    var filteredBreeds by remember { mutableStateOf(emptySet<String>()) }

    LaunchedEffect(Unit) {
        favoriteImagesFlow
            .asUiState()
            .collect { images ->
                favoriteImagesResult = images
                if (images is UiState.Success) {
                    val chipsLabels = images.data.map { it.breedName }.toSet()
                    filteredBreeds = filteredBreeds.intersect(chipsLabels)
                }
            }
    }

    LaunchedEffect(Unit) {
        events.collect { event ->
            if (event is Event.ToggleSelectedBreed) {
                val toggleBreed = event.breed
                filteredBreeds = if (toggleBreed.selected) {
                    filteredBreeds - toggleBreed.label
                } else {
                    filteredBreeds + toggleBreed.label
                }
            }
        }
    }

    return favoriteImagesResult.mapSuccess { favoriteImages ->
        val chipsLabels = favoriteImages.map { it.breedName }.toSet()
        val filteredImages = favoriteImages.filter {
            filteredBreeds.isEmpty() || it.breedName in filteredBreeds
        }

        val filterChipsInfo = chipsLabels.map { label ->
            ChipInfo(
                label = label,
                selected = label in filteredBreeds
            )
        }.toSet()


        FavoritesModel(
            dogImages = filteredImages,
            filterChipsInfo = filterChipsInfo
        )
    }
}