@file:OptIn(ExperimentalMaterial3Api::class)

package ui.images

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import data.KtorHttpClient
import data.images.BreedImagesApi
import data.images.BreedImagesApiImpl
import data.images.ImagesRepositoryImpl
import domain.breeds.buildDisplayName
import domain.images.GetBreedImagesUseCase
import domain.images.buildBreedKey
import utils.capitalizeWords
import utils.common.UiState
import utils.common.UiStateWrapper
import utils.common.asUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesScreen(
//    navController: NavController,
    breed: String,
    subBreed: String?
) {
//    val imagesViewModel: ImagesViewModel = hiltViewModel()
//    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    val getBreedImagesUseCase = GetBreedImagesUseCase(
        imagesRepository = ImagesRepositoryImpl(
            dogBreedApiService = BreedImagesApiImpl(
                KtorHttpClient()
            )
        )
    )
    val breedKey = buildBreedKey(subBreed, breed)

    val dogImages by getBreedImagesUseCase.invoke(breedKey)
        .asUiState()
        .collectAsState(initial = UiState.Loading)

    println(dogImages)

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(key1 = breed) {
//        imagesViewModel.fetchDogImages(breed, subBreed)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        buildDisplayName(breed, subBreed).capitalizeWords(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
//                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "ArrowBack",
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            UiStateWrapper(dogImages) { dogImageList ->
                DogImagesGrid(
                    images = dogImageList
                ) { dogImage ->
//                    favoritesViewModel.toggleFavorite(dogImage)
                }
            }
        }
    }
}



