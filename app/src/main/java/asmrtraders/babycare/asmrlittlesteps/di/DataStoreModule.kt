package asmrtraders.babycare.asmrlittlesteps.di

import asmrtraders.babycare.asmrlittlesteps.data.datastore.GCZCTOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { GCZCTOnboardingPrefs(androidContext()) }
}