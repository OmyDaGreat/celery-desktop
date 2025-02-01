package xyz.malefic.celery

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.application
import xyz.malefic.celery.screens.App1
import xyz.malefic.celery.screens.Home
import xyz.malefic.celery.screens.ThemeSelector
import xyz.malefic.celery.util.ThemeManager.loadTheme
import xyz.malefic.compose.comps.precompose.NavWindow
import xyz.malefic.compose.comps.text.typography.Heading1
import xyz.malefic.compose.engine.factory.RowFactory
import xyz.malefic.compose.engine.factory.div
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.background
import xyz.malefic.compose.engine.fuel.center
import xyz.malefic.compose.engine.fuel.divide
import xyz.malefic.compose.engine.fuel.fuel
import xyz.malefic.compose.nav.RouteManager
import xyz.malefic.compose.nav.RouteManager.RoutedNavHost
import xyz.malefic.compose.nav.RouteManager.RoutedSidebar
import xyz.malefic.compose.nav.RouteManager.navi
import xyz.malefic.compose.theming.MaleficTheme
import xyz.malefic.ext.list.get

/**
 * Entry point of the application that sets up the main navigation window.
 * It determines the theme based on the system's current theme (dark or light),
 * applies the selected theme, and initializes the route manager with the
 * composable map and configuration loader. The navigation menu is then displayed.
 */
fun main() =
    application {
        var selectedTheme by remember { mutableStateOf("light") }

        NavWindow(onCloseRequest = ::exitApplication) {
            RouteManager.initialize {
                startup("home") { Home(navi) }
                dynamic("app1", true, "id", "name?") { params -> App1(id = params[0]!!, name = params[1, null]) }
                dynamic("hidden", true, "text?") { params -> Heading1(text = params[0, "Nope."]) }
                static("themes") { ThemeSelector { theme -> selectedTheme = theme } }
            }

            val themeInputStream = loadTheme(selectedTheme) ?: throw IllegalArgumentException("Theme file not found")

            key(selectedTheme) {
                MaleficTheme(themeInputStream) {
                    NavigationMenu()
                }
            }
        }
    }

/**
 * Composable function that defines the navigation menu layout. It includes a sidebar and a
 * content area separated by a divider.
 */
@Composable
fun NavigationMenu() {
    RowFactory {
        fuel { RoutedSidebar() }.divide()()
        RoutedNavHost()
    } / {
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    } *= {
        center()
        background()
    }
}
