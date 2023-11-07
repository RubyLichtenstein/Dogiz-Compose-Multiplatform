@file:OptIn(ExperimentalMaterial3Api::class)

package ui.breeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.breeds.BreedEntity
import domain.breeds.GetBreedsUseCase
import org.koin.compose.koinInject
import utils.common.UiState
import utils.common.UiStateWrapper
import utils.common.asUiState

@ExperimentalMaterial3Api
@Composable
fun BreedsScreen(
    navigateToDogImages: (BreedEntity) -> Unit
) {
    val getBreedsUseCase = koinInject<GetBreedsUseCase>()

    val breedListState by getBreedsUseCase().asUiState().collectAsState(initial = UiState.Loading)

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Dogiz",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            UiStateWrapper(breedListState) { data ->
                BreedList(
                    breeds = data,
                    onItemClick = navigateToDogImages
                )
            }
        }
    }
}

@Composable
fun BreedList(breeds: List<BreedEntity>, onItemClick: (BreedEntity) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(breeds) { breed ->
            BreedListItem(breed = breed, onClick = { onItemClick(breed) })
        }
    }
}

@Composable
fun BreedListItem(breed: BreedEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 4.dp)
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = breed.displayName(),
                )
            },
            Modifier.clickable(onClick = onClick),
            tonalElevation = 4.dp,
        )
    }
}