package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import asmrtraders.babycare.asmrlittlesteps.R
import asmrtraders.babycare.asmrlittlesteps.data.model.Product
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.ProductDetailsViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier = modifier.fillMaxSize()) {
        val product = (state as? DataUiState.Populated)?.data
        if (product != null) {
            ProductContent(product) {
                viewModel.addProductToCart()
                cartAdded = true
            }
        }
        AnimatedVisibility(
            visible = cartAdded,
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter),
            enter = slideInVertically { it },
            exit = fadeOut(),
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Text("Added to cart", modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
private fun ProductContent(product: Product, onAdd: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.secondary)
            Text(product.title, style = MaterialTheme.typography.headlineMedium)
            Text(
                stringResource(R.string.gczct_price, product.price),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                product.description,
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                "Selected for dependable quality, everyday comfort and an easier routine for parents.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Button(onClick = onAdd, modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
                Text(stringResource(R.string.gczct_button_add_to_cart_label))
            }
        }
    }
}
