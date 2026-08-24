package asmrtraders.babycare.asmrlittlesteps.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
private val AppColors = lightColorScheme(
    primary = Coral,
    onPrimary = WarmWhite,
    secondary = Teal,
    onSecondary = WarmWhite,
    background = Ivory,
    onBackground = Ink,
    surface = WarmWhite,
    onSurface = Ink,
    surfaceVariant = Blush,
    onSurfaceVariant = Muted,
    outline = Border,
    tertiary = Success,
)
@Composable
fun ProductAppGCZCTTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(colorScheme = AppColors, typography = AppTypography, content = content)
}
