package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.splash
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import asmrtraders.babycare.asmrlittlesteps.R
import asmrtraders.babycare.asmrlittlesteps.ui.theme.Coral
import asmrtraders.babycare.asmrlittlesteps.ui.theme.Teal
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.GCZCTSplashVM
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: GCZCTSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(800))
        delay(700)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }
    Column(
        modifier = modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Coral, Teal))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "LS",
            modifier = Modifier.alpha(progress.value).scale(0.8f + progress.value * 0.2f),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = stringResource(R.string.gczct_app_name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = "Care for every little step",
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
