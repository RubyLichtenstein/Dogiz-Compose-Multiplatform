import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import data.dataModule
import domain.domainModule
import org.koin.compose.KoinApplication
import ui.navigation.MainNavigator

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
            MainNavigator()
        }
    }
}

expect fun getPlatformName(): String