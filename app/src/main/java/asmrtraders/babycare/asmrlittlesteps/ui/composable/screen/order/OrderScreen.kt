package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.order
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    val orders = (state as? DataUiState.Populated)?.data.orEmpty().sortedByDescending { it.timestamp }
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Purchase history", style = MaterialTheme.typography.headlineMedium)
        if (orders.isEmpty()) {
            Text("No orders yet", modifier = Modifier.padding(top = 24.dp))
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(vertical = 16.dp)) {
                items(orders, key = { it.orderNumber }) { order ->
                    Card {
                        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                            Text("Order #" + order.orderNumber, style = MaterialTheme.typography.titleMedium)
                            Text(order.timestamp.toLocalDate().toString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(order.description, modifier = Modifier.padding(vertical = 8.dp))
                            Text("£%.2f".format(order.price), style = MaterialTheme.typography.titleLarge)
                            Text("Collection held in store for 24 hours", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
