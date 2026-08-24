package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import asmrtraders.babycare.asmrlittlesteps.R
import asmrtraders.babycare.asmrlittlesteps.data.model.Product
import asmrtraders.babycare.asmrlittlesteps.data.model.ProductCategory
import asmrtraders.babycare.asmrlittlesteps.ui.state.DataUiState
import asmrtraders.babycare.asmrlittlesteps.ui.viewmodel.ProductViewModel
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsStateWithLifecycle()
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val products = (state as? DataUiState.Populated)?.data.orEmpty()
    val filtered = if (category == null) products else products.filter { it.category == category }
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.gczct_app_name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp),
        )
        Text(
            text = "Thoughtful essentials for growing families",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (products.isNotEmpty()) {
            FeaturedProduct(products.first(), onNavigateToProductDetails)
        }
        LazyRow(
            contentPadding = PaddingValues(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                FilterChip(
                    selected = category == null,
                    onClick = { category = null },
                    label = { Text("All") },
                )
            }
            items(ProductCategory.entries) { item ->
                FilterChip(
                    selected = category == item,
                    onClick = { category = item },
                    label = { Text(stringResource(item.titleRes)) },
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(filtered, key = { it.id }) { product ->
                ProductCard(product, onNavigateToProductDetails)
            }
        }
    }
}

@Composable
private fun FeaturedProduct(product: Product, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(210.dp).padding(top = 16.dp).clickable { onClick(product.id) },
        shape = RoundedCornerShape(24.dp),
    ) {
        Box {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Surface(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Editor's pick", color = MaterialTheme.colorScheme.primary)
                    Text(product.title, style = MaterialTheme.typography.titleLarge)
                    Text(stringResource(R.string.gczct_price, product.price))
                }
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick(product.id) },
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            modifier = Modifier.fillMaxWidth().height(if (product.id % 2 == 0) 210.dp else 160.dp).clip(RoundedCornerShape(18.dp)),
            contentScale = ContentScale.Crop,
        )
        Text(product.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
        Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(
            stringResource(R.string.gczct_price, product.price),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
