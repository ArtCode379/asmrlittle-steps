package asmrtraders.babycare.asmrlittlesteps.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import asmrtraders.babycare.asmrlittlesteps.data.entity.CartItemEntity
import asmrtraders.babycare.asmrlittlesteps.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {

    @Query("SELECT * FROM cart_items")
    fun observeAll(): Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cart_items WHERE id = :id")
    fun observeById(id: Int): Flow<CartItemEntity?>

    @Query("SELECT * FROM cart_items")
    suspend fun getAll(): List<CartItemEntity>

    @Query("SELECT * FROM cart_items WHERE id = :id")
    suspend fun getById(id: Int): CartItemEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(cartItemEntity: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM cart_items")
    suspend fun deleteAll()

    @Query("UPDATE cart_items SET quantity = quantity + 1 WHERE id = :productId")
    suspend fun incrementQuantity(productId: Int): Int

    @Transaction
    suspend fun incrementProductQuantityOrAdd(product: Product) {
        val updated = incrementQuantity(product.id)

        if (updated == 0) {
            save(
                CartItemEntity(
                    id = product.id,
                    quantity = 1,
                )
            )
        }
    }

    @Query("UPDATE cart_items SET quantity = quantity - 1 WHERE id = :productId AND quantity > 1")
    suspend fun decrementQuantity(productId: Int): Int

    @Query("DELETE FROM cart_items WHERE id = :id AND quantity = 1")
    suspend fun deleteIfQuantityOne(id: Int)

    @Transaction
    suspend fun decrementProductQuantityOrRemove(product: Product) {
        val updated = decrementQuantity(product.id)
        if (updated == 0) {
            deleteIfQuantityOne(product.id)
        }
    }
}