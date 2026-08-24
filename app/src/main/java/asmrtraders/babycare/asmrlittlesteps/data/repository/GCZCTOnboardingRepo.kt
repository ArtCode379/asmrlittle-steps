package asmrtraders.babycare.asmrlittlesteps.data.repository

import asmrtraders.babycare.asmrlittlesteps.data.datastore.GCZCTOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GCZCTOnboardingRepo(
    private val gczctOnboardingStoreManager: GCZCTOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return gczctOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            gczctOnboardingStoreManager.setOnboardedState(state)
        }
    }
}