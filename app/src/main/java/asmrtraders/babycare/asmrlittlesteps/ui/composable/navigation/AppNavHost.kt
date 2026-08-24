package asmrtraders.babycare.asmrlittlesteps.ui.composable.navigation

//[COMMON][import_fillMaxSize]
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
//[COMMON][import_DetailsScreen]
//[COMMON][import_PrepScreen]
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.cart.CartScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.checkout.CheckoutScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.home.HomeScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.onboarding.OnboardingScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.order.OrdersScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.productdetails.ProductDetailsScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.settings.SettingsScreen
import asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        //[COMMON][PrepComposable]

        //[COMMON][DetailsComposable]

        composable<NavRoute.Splash> {
            SplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            OnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            HomeScreen(
                onNavigateToProductDetails = { id: Int ->
                    navController.navigate(
                        route = NavRoute.ProductDetails(id = id)
                    )
                }
            )
        }

        composable<NavRoute.ProductDetails> { backStackEntry ->
            val productDetails: NavRoute.ProductDetails = backStackEntry.toRoute()
            ProductDetailsScreen(
                productId = productDetails.id,
            )
        }

        composable<NavRoute.Cart> {
            CartScreen(
                onNavigateToCheckoutScreen = {
                    navController.navigate(NavRoute.Checkout)
                }
            )
        }

        composable<NavRoute.Checkout> {
            CheckoutScreen(
                onNavigateToOrdersScreen = {
                    navController.navigate(NavRoute.Orders) {
                        popUpTo(NavRoute.Home) { inclusive = false }
                    }
                }
            )
        }

        composable<NavRoute.Orders> {
            OrdersScreen()
        }

        composable<NavRoute.Settings> {
            SettingsScreen()
        }
    }
}