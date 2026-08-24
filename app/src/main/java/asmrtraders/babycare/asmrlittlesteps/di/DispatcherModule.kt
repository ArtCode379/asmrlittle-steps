package asmrtraders.babycare.asmrlittlesteps.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DispatchersQualifiers {
    val IO = named("IO")
    val Default = named("Default")
    val Main = named("Main")
}

val dispatcherModule = module {
    single<CoroutineDispatcher>(DispatchersQualifiers.IO) {
        Dispatchers.IO
    }

    single<CoroutineDispatcher>(DispatchersQualifiers.Default) {
        Dispatchers.Default
    }

    single<CoroutineDispatcher>(DispatchersQualifiers.Main) {
        Dispatchers.Main
    }
}