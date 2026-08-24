package asmrtraders.babycare.asmrlittlesteps.ui.composable.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState

@Composable
fun <T> GCZCTContentWrapper(
    modifier: Modifier = Modifier,
    dataState: DataUiState<T>,
    dataPopulated: @Composable (() -> Unit),
    dataEmpty: @Composable (() -> Unit),
    dataInitial: @Composable (() -> Unit) = {},
) {
    Box(modifier = modifier) {
        when (dataState) {
            is DataUiState.Populated -> {
                dataPopulated()
            }

            DataUiState.Empty -> {
                dataEmpty()
            }

            DataUiState.Initial -> {
                dataInitial()
            }
        }
    }
}