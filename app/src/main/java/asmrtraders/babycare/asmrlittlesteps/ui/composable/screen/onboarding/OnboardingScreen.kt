package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.GCZCTOnboardingVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private val pages = listOf(
    "Everything for tiny beginnings" to "Discover thoughtful feeding, clothing and care essentials chosen for growing families.",
    "Ready for days out" to "Find carriers, pushchairs and practical travel companions for every little adventure.",
    "Support for every parent" to "Prepare for maternity and baby's arrival with calm guidance and dependable products.",
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: GCZCTOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    LaunchedEffect(completed) {
        if (completed) onNavigateToHomeScreen()
    }
    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(text = "0" + (page + 1), color = MaterialTheme.colorScheme.secondary)
                Text(text = pages[page].first, style = MaterialTheme.typography.displaySmall)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = pages[page].second,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            pages.indices.forEach { index ->
                Surface(
                    modifier = Modifier.padding(4.dp).size(if (index == pagerState.currentPage) 24.dp else 8.dp, 8.dp),
                    shape = CircleShape,
                    color = if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                ) {}
            }
        }
        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    viewModel.setOnboarded()
                } else {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (pagerState.currentPage == pages.lastIndex) "Get Started" else "Next")
        }
    }
}
