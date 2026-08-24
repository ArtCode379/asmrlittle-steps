package asmrtraders.babycare.asmrlittlesteps.ui.composable.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object Splash : NavRoute()

    @Serializable
    object Onboarding : NavRoute()

    @Serializable
    object Home : NavRoute()

    @Serializable
    object Cart : NavRoute()

    @Serializable
    object Checkout : NavRoute()

    @Serializable
    object Orders : NavRoute()

    @Serializable
    object Settings : NavRoute()

    @Serializable
    data class ProductDetails(val id: Int) : NavRoute()

    //[COMMON][PrepRoute]

    //[COMMON][DetailsRoute]
}