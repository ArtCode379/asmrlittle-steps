package asmrtraders.babycare.asmrlittlesteps.ui.composable.approot

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import asmrtraders.babycare.asmrlittlesteps.ui.composable.navigation.NavRoute

data class BottomNavItem(
    @field:StringRes val titleRes: Int,
    val icon: ImageVector,
    val route: NavRoute,
)

@Composable
fun AppBottomBar(
    itemsInCart: Int,
    currentDestination: NavDestination?,
    navigationItems: List<BottomNavItem>,
    onNavigateToRoute: (BottomNavItem) -> Unit,
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = isSelectedDestination(currentDestination, item.route),

                onClick = { onNavigateToRoute(item) },

                icon = {
                    if (item.route == NavRoute.Cart) {
                        BadgedBox(
                            badge = {
                                if (itemsInCart > 0) {
                                    Badge {
                                        Text(itemsInCart.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(item.titleRes),
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.titleRes),
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },

                label = {
                    Text(text = stringResource(item.titleRes))
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                )
            )
        }
    }
}

private fun isSelectedDestination(destination: NavDestination?, route: NavRoute): Boolean {
    return destination?.let {
        destination.hierarchy.any { navDestination -> navDestination.hasRoute(route::class) }
    } ?: return false
}