package asmrtraders.babycare.asmrlittlesteps.di

import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.AppViewModel
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.CartViewModel
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.CheckoutViewModel
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.GCZCTOnboardingVM
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.OrderViewModel
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.ProductDetailsViewModel
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.ProductViewModel
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.GCZCTSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        GCZCTSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        GCZCTOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}