package com.barros9.beerappkmm.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.barros9.beerappkmm.android.theme.colorAccent
import com.barros9.beerappkmm.android.theme.colorPrimary
import com.barros9.beerappkmm.android.theme.colorPrimaryDark

private val DarkColorPalette = darkColors(
    primary = colorPrimary,
    primaryVariant = colorPrimaryDark,
    secondary = colorAccent
)

private val LightColorPalette = lightColors(
    primary = colorPrimary,
    primaryVariant = colorPrimaryDark,
    secondary = colorAccent
)

@Composable
fun BeerAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // they are the same
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
