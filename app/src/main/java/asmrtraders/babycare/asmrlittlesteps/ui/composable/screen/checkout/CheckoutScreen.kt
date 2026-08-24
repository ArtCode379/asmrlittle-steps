package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val invalidEmail by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    if (orderState is DataUiState.Populated) {
        CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    }
    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text(
            "We will hold your order in store for 24 hours after confirmation.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        OutlinedTextField(
            value = viewModel.customerFirstName,
            onValueChange = viewModel::updateCustomerFirstName,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = viewModel.customerLastName,
            onValueChange = viewModel::updateCustomerLastName,
            label = { Text("Collection address") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = viewModel.customerEmail,
            onValueChange = viewModel::updateCustomerEmail,
            label = { Text("Email or phone") },
            isError = invalidEmail,
            supportingText = {
                if (invalidEmail) Text("Enter a valid email address for confirmation")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
        )
        Card {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("Order summary", style = MaterialTheme.typography.titleMedium)
                Text("Your basket items will be reserved for collection.")
                Text("Collection window: 24 hours", color = MaterialTheme.colorScheme.primary)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = viewModel::placeOrder,
            enabled = viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Place Order")
        }
    }
}
