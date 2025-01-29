package xyz.malefic.celery

import java.io.InputStream

object ThemeManager {
    private const val THEME_PATH = "/theme/"

    fun loadTheme(theme: String): InputStream? = ThemeManager::class.java.getResourceAsStream("$THEME_PATH$theme.json")

    fun getAvailableThemes(): List<String> = listOf("dark", "light", "mint", "galaxy", "sunset", "lavender", "sand")
}
