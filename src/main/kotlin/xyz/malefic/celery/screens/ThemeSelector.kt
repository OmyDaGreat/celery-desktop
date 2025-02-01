package xyz.malefic.celery.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.malefic.celery.util.ThemeManager
import xyz.malefic.compose.comps.text.typography.Body1
import xyz.malefic.compose.engine.factory.BoxFactory
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.center
import xyz.malefic.compose.engine.fuel.clickable
import xyz.malefic.compose.engine.fuel.fillMaxWidth
import xyz.malefic.compose.engine.fuel.padding
import java.util.Locale

@Composable
fun ThemeSelector(onThemeSelected: (String) -> Unit) {
    val themes = ThemeManager.getAvailableThemes()

    ColumnFactory {
        themes.forEach { theme ->
            BoxFactory {
                Body1(
                    text = theme.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    modifier = Modifier.padding(6.dp),
                )
            } *= {
                fillMaxWidth()
                padding(6.dp)
                clickable {
                    onThemeSelected(theme)
                }
                padding(6.dp)
            }
        }
    } *= {
        center()
    }
}
