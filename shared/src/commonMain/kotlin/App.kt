import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.breeds.BreedsScreen
import ui.images.DogImage

sealed class Screen {
    data object BreedsList : Screen()
    data object Favorites : Screen()
    data class DogImages(val breedKey: String) : Screen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme {
        val currentScreen = remember { mutableStateOf<Screen>(Screen.BreedsList) }

        when (val screen = currentScreen.value) {
            is Screen.BreedsList -> BreedsScreen { breed ->
                currentScreen.value = Screen.DogImages(breed.route)
            }

            is Screen.Favorites -> BreedsScreen {}
            is Screen.DogImages -> DogImage(screen.breedKey)
        }
    }
}

expect fun getPlatformName(): String