@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package ui.favorites

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import domain.images.DogImage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import ui.images.DogImagesGrid
import utils.common.UiState
import utils.common.UiStateWrapper

@Composable
fun FavoritesScreen() {
    val coroutineScope = rememberCoroutineScope()
    val uiState by coroutineScope.launchMolecule(mode = RecompositionMode.ContextClock) {
        FavoritesPresenter(
            events = flowOf(),
            favoriteImagesFlow = flowOf(emptyList())
        )
    }.collectAsState()

    PureFavoritesScreen(
        state = uiState,
//        navController = navController,
        onToggleSelectedBreed = {
//            viewModel.toggleBreedFilter(it)
        },
        onToggleFavorite = {
//            viewModel.toggleFavorite(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PureFavoritesScreen(
    state: UiState<FavoritesModel>,
//    navController: NavController,
    onToggleSelectedBreed: (ChipInfo) -> Unit,
    onToggleFavorite: (DogImage) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults
        .exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(/*navController, */scrollBehavior)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            UiStateWrapper(state) {
                if (it.dogImages.isEmpty()) {
                    EmptyScreen()
                } else {
                    Column {
                        BreedFilter(it.filterChipsInfo, onToggleSelectedBreed)
                        DogImagesGrid(images = it.dogImages, onToggleFavorite = onToggleFavorite)
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No favorites yet")
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(
//    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    LargeTopAppBar(
        title = {
            Text(
                "Favorites",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
//        navigationIcon = {
//            IconButton(onClick = {
////                navController.popBackStack()
//            }) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Back",
//                )
//            }
//        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BreedFilter(
    breeds: Set<ChipInfo>,
    onToggleSelectedBreed: (ChipInfo) -> Unit
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
    ) {
        breeds.forEach { breed ->
            FilterChip(
                selected = breed.selected,
                onClick = { onToggleSelectedBreed(breed) },
                label = { Text(breed.label) },
                trailingIcon = {
                    if (breed.selected) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}