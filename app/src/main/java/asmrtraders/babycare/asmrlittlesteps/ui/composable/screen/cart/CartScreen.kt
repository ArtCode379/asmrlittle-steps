package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import asmrtraders.babycare.asmrlittlesteps.R
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.CartViewModel
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    val items = (state as? DataUiState.Populated)?.data.orEmpty()
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Your basket", style = MaterialTheme.typography.headlineMedium)
        if (items.isEmpty()) {
            Box(modifier = Modifier.weight(1f)) {
                Text("Your basket is ready for little essentials.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Button(onClick = { onNavigateToCheckoutScreen() }, enabled = false, modifier = Modifier.fillMaxWidth()) {
                Text("Start Shopping")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(items, key = { it.productId }) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            AsyncImage(
                                model = item.productImageUrl,
                                contentDescription = item.productTitle,
                                modifier = Modifier.size(72.dp),
                                contentScale = ContentScale.Crop,
                            )
                            Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                                Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                                Text(stringResource(R.string.gczct_price, item.productPrice))
                                Row {
                                    TextButton(
                                        onClick = {
                                            if (item.quantity == 1) {
                                                viewModel.deleteFromCart(item.productId)
                                            } else {
                                                viewModel.decrementItemInCart(item.productId)
                                            }
                                        },
                                    ) { Text("−") }
                                    Text(item.quantity.toString(), modifier = Modifier.padding(top = 12.dp))
                                    TextButton(onClick = { viewModel.incrementProductInCart(item.productId) }) { Text("+") }
                                }
                            }
                            IconButton(onClick = { viewModel.deleteFromCart(item.productId) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Remove item")
                            }
                        }
                    }
                }
            }
            HorizontalDivider()
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", style = MaterialTheme.typography.titleLarge)
                Text(stringResource(R.string.gczct_price, total), style = MaterialTheme.typography.titleLarge)
            }
            Button(onClick = onNavigateToCheckoutScreen, modifier = Modifier.fillMaxWidth()) {
                Text("Proceed to Checkout")
            }
        }
    }
}
