package ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.breeds.BreedEntity
import ui.breeds.BreedsScreen
import ui.images.ImagesScreen

@OptIn(ExperimentalMaterial3Api::class)
object BreedsScreenNav : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        BreedsScreen {
            navigator.push(ImagesScreenNav(it))
        }
    }
}

data class ImagesScreenNav(val breedEntity: BreedEntity) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ImagesScreen(breedEntity) {
            navigator.pop()
        }
    }
}