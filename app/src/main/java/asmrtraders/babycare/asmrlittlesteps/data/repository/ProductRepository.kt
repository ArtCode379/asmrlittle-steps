package asmrtraders.babycare.asmrlittlesteps.data.repository

import asmrtraders.babycare.asmrlittlesteps.data.model.Product
import asmrtraders.babycare.asmrlittlesteps.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        product(1, "Organic First Tastes Set", "Smooth fruit and vegetable purees for little explorers.", ProductCategory.FEEDING, 18.50, "1591163119107-8f3a7832529f"),
        product(2, "CloudSoft Newborn Nappies", "Breathable, fragrance-free nappies. Pack of 40.", ProductCategory.NAPPIES, 9.95, "1584839404042-8bc21d240e91"),
        product(3, "Little Meadow Sleepsuit", "Organic cotton sleepsuit with flat seams.", ProductCategory.CLOTHING, 16.00, "1522771930-78848d9293e8"),
        product(4, "Compact City Pushchair", "Lightweight one-hand fold pushchair with canopy.", ProductCategory.TRAVEL, 189.00, "1591088398332-8a7791972843"),
        product(5, "Calm Bath Essentials", "Tear-free wash, lotion and a soft washcloth.", ProductCategory.CARE, 22.00, "1607006483225-58d94f2203b3"),
        product(6, "Maternity Support Pillow", "Full-length support for comfortable side sleeping.", ProductCategory.MATERNITY, 42.00, "1544126592-807ade215a0b"),
        product(7, "Bamboo Feeding Bowl", "A suction bowl with a soft silicone spoon.", ProductCategory.FEEDING, 14.50, "1600565193348-f74bd3c7ccdf"),
        product(8, "Woodland Knit Cardigan", "A warm cotton knit made for easy layering.", ProductCategory.CLOTHING, 24.00, "1519457431-44ccd64a579b"),
        product(9, "Everyday Baby Carrier", "Ergonomic carrier with adjustable newborn support.", ProductCategory.TRAVEL, 64.00, "1578662996442-48f60103fc96"),
        product(10, "Gentle Grooming Kit", "Rounded tools packed in a tidy travel case.", ProductCategory.CARE, 19.50, "1619451334792-150fd785ee74"),
        product(11, "Hospital Bag Organiser", "Six labelled pouches for parent and baby.", ProductCategory.MATERNITY, 28.00, "1544816155-12df9643f363"),
        product(12, "Muslin Swaddle Trio", "Three lightweight cotton muslins.", ProductCategory.CARE, 21.00, "1527628173875-3c7bfd28ad78"),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }

    private fun product(
        id: Int,
        title: String,
        description: String,
        category: ProductCategory,
        price: Double,
        imageId: String,
    ): Product {
        return Product(
            id = id,
            title = title,
            description = description,
            category = category,
            price = price,
            imageUrl = "https://images.unsplash.com/photo-" + imageId + "?w=1200",
        )
    }
}
