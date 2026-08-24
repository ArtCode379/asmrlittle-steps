package asmrtraders.babycare.asmrlittlesteps.ui.state

sealed class DataUiState<out T> {

    object Initial : DataUiState<Nothing>()
    object Empty : DataUiState<Nothing>()

    data class Populated<T : Any>(val data: T) : DataUiState<T>()

    companion object {

        fun <T> from(list: List<T>) = if (list.isEmpty()) Empty else Populated(list)

        fun <T : Any> from(data: T?): DataUiState<T> = if (data == null) Empty else Populated(data)
    }
}