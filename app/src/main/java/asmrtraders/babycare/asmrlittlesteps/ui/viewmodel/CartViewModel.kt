package asmrtraders.babycare.asmrlittlesteps.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asmrtraders.babycare.asmrlittlesteps.data.repository.CartRepository
import asmrtraders.babycare.asmrlittlesteps.data.repository.ProductRepository
import asmrtraders.babycare.asmrlittlesteps.ui.state.CartItemUiState
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {
    private val _cartItemsState =
        MutableStateFlow<DataUiState<List<CartItemUiState>>>(DataUiState.Initial)
    val cartItemsState: StateFlow<DataUiState<List<CartItemUiState>>>
        get() = _cartItemsState.asStateFlow()

    private val _totalPrice = MutableStateFlow<Double>(0.0)
    val totalPrice: StateFlow<Double>
        get() = _totalPrice.asStateFlow()

    init {
        observeCartItems()
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            combine(
                cartRepository.observeAll(),
                productRepository.observeAll()
            ) { cartItems, products ->

                val productsMap = products.associateBy { it.id }

                val uiItems = cartItems.mapNotNull { cartItem ->
                    productsMap[cartItem.id]?.let { product ->
                        CartItemUiState(
                            productId = product.id,
                            productTitle = product.title,
                            productPrice = product.price,
                            quantity = cartItem.quantity,
                            productImageUrl = product.imageUrl,
                        )
                    }
                }

                if (uiItems.isEmpty()) DataUiState.Empty
                else {
                    _totalPrice.value = calculateTotalPrice(uiItems)
                    DataUiState.Populated(uiItems)
                }
            }.collect { state ->
                _cartItemsState.value = state
            }
        }
    }

    fun incrementProductInCart(productId: Int) {
        viewModelScope.launch {
            cartRepository.incrementQuantity(productId)
        }
    }

    fun deleteFromCart(productId: Int) {
        viewModelScope.launch {
            cartRepository.deleteById(productId)
        }
    }

    fun decrementItemInCart(productId: Int) {
        viewModelScope.launch {
            productRepository.getById(productId)?.let { product ->
                cartRepository.decrementProductQuantityOrRemove(product)
            }
        }
    }

    private fun calculateTotalPrice(items: List<CartItemUiState>): Double = items.sumOf { item ->
        item.productPrice * item.quantity
    }
}