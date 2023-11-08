import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.dataModule
import domain.breeds.BreedEntity
import domain.domainModule
import org.koin.compose.KoinApplication
import ui.breeds.BreedsScreen
import ui.images.ImagesScreen

@OptIn(ExperimentalMaterial3Api::class)
object BreedsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        BreedsScreen {
            navigator.push(ImagesScreen(it))
        }
    }
}

data class ImagesScreen(val breedEntity: BreedEntity) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ImagesScreen(breedEntity) {
            navigator.pop()
        }
    }
}

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
            Navigator(BreedsScreen)
        }
    }
}

expect fun getPlatformName(): String