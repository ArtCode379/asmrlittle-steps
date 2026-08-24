package asmrtraders.babycare.asmrlittlesteps.ui.composable.approot

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import asmrtraders.babycare.asmrlittlesteps.R
import asmrtraders.babycare.asmrlittlesteps.ui.composable.navigation.NavRoute
import kotlin.reflect.KClass

private val canNavigateBackRoutes: List<KClass<out NavRoute>> = listOf(
    NavRoute.ProductDetails::class,
    NavRoute.Checkout::class,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentDestination: NavDestination?,
    isCartNotEmpty: Boolean,
    onClearCartIconClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val isCartScreen = currentDestination?.hasRoute(route = NavRoute.Cart::class) == true
    val canNavigateBack = currentDestination.matchesAnyRoute(canNavigateBackRoutes)

    TopAppBar(
        title = {
            Text(
                text = getTitle(currentDestination)?.let { stringResource(it) }.orEmpty()
            )
        },

        actions = {
            if (isCartScreen) {
                IconButton(
                    onClick = { onClearCartIconClick() },
                    enabled = isCartNotEmpty,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Clear Cart",
                        modifier = Modifier.size(24.dp),
                        tint = if (isCartNotEmpty)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    )
                }
            }
        },

        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate Back",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

private fun getTitle(currentDestination: NavDestination?): Int? {
    return when {
        currentDestination == null -> null

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Home::class) } -> {
            R.string.gczct_top_bar_home_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.ProductDetails::class) } -> {
            R.string.gczct_top_bar_product_details_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Cart::class) } -> {
            R.string.gczct_top_bar_cart_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Checkout::class) } -> {
            R.string.gczct_top_bar_checkout_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Orders::class) } -> {
            R.string.gczct_top_bar_orders_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Settings::class) } -> {
            R.string.gczct_top_bar_settings_title
        }

        else -> null
    }
}