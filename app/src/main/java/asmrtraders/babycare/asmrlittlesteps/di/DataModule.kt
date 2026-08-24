package asmrtraders.babycare.asmrlittlesteps.di

import asmrtraders.babycare.asmrlittlesteps.data.repository.CartRepository
import asmrtraders.babycare.asmrlittlesteps.data.repository.GCZCTOnboardingRepo
import asmrtraders.babycare.asmrlittlesteps.data.repository.OrderRepository
import asmrtraders.babycare.asmrlittlesteps.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        GCZCTOnboardingRepo(
            gczctOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}