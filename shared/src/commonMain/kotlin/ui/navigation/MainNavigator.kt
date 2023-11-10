package ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import ui.favorites.FavoriteCountBadge


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val options = tab.options
    NavigationBarItem(
        icon = {
            if (tab.options.title == "Favorites") {
                FavoriteCountBadge()
            } else {
                Icon(painter = options.icon!!, contentDescription = options.title)
            }
        },
        label = { Text(options.title) },
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
    )
}

@Composable
fun MainNavigator() {
    TabNavigator(BreedsTab) {
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    NavigationBar {
                        TabNavigationItem(BreedsTab)
                        TabNavigationItem(FavoritesTab)
                    }
                }
            }
        ) {
            Box(Modifier.padding(it)) {
                CurrentTab()
            }
        }
    }
}