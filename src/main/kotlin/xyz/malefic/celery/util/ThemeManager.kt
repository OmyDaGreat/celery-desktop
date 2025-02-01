package xyz.malefic.celery.util

import java.io.InputStream

/**
 * Object responsible for managing themes in the application.
 */
object ThemeManager {
    private const val THEME_PATH = "/theme/"

    /**
     * Loads the theme file as an InputStream.
     *
     * @param theme The name of the theme to load.
     * @return An InputStream of the theme file, or null if the file is not found.
     */
    fun loadTheme(theme: String): InputStream? = ThemeManager::class.java.getResourceAsStream("$THEME_PATH$theme.json")

    /**
     * Retrieves a list of available themes.
     *
     * @return A list of theme names.
     */
    fun getAvailableThemes(): List<String> = listOf("dark", "light", "mint", "galaxy", "sunset", "lavender", "sand")
}
