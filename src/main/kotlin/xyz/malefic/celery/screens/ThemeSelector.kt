package xyz.malefic.celery.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sun.org.apache.xalan.internal.lib.ExsltStrings.padding
import xyz.malefic.celery.ThemeManager
import xyz.malefic.compose.comps.text.typography.Heading2
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.times
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.padding
import java.util.Locale

@Composable
fun ThemeSelector(onThemeSelected: (String) -> Unit) {
    val themes = ThemeManager.getAvailableThemes()
    val showDialog = remember { mutableStateOf(true) }

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            properties = DialogProperties(),
        ) {
            ColumnFactory {
                themes.forEach { theme ->
                    Heading2(
                        text = theme.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onThemeSelected(theme)
                                    showDialog.value = false
                                }.padding(8.dp),
                    )
                }
            } *= {
                padding(16.dp)
            }
        }
    }
}
