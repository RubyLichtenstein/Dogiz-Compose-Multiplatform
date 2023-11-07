import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import data.dataModule
import domain.breeds.BreedEntity
import domain.domainModule
import org.koin.compose.KoinApplication
import ui.breeds.BreedsScreen
import ui.images.ImagesScreen

sealed class Screen {
    data object BreedsList : Screen()
    data object Favorites : Screen()
    data class DogImages(val breedEntity: BreedEntity) : Screen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    KoinApplication(application = {
        modules(
            listOf(
                dataModule,
                domainModule
            )
        )
    }) {
        MaterialTheme {
            val currentScreen = remember { mutableStateOf<Screen>(Screen.BreedsList) }

            when (val screen = currentScreen.value) {
                is Screen.BreedsList -> BreedsScreen { breed ->
                    currentScreen.value = Screen.DogImages(breed)
                }

                is Screen.Favorites -> BreedsScreen {}
                is Screen.DogImages -> ImagesScreen(
                    screen.breedEntity
                ) {
                    currentScreen.value = Screen.BreedsList
                }
            }
        }
    }
}

expect fun getPlatformName(): String