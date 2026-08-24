package asmrtraders.babycare.asmrlittlesteps.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("orders")
data class OrderEntity(
    @PrimaryKey @ColumnInfo(name = "order_number") val orderNumber: String,

    /**
     * Normally contains a list of products in the form: ItemA x 1, ItemB x 3, ItemC x 2, etc.
     * It may contain any other content as well.
     */
    val description: String,
    @ColumnInfo(name = "customer_first_name") val customerFirstName: String,
    @ColumnInfo(name = "customer_last_name") val customerLastName: String,
    @ColumnInfo(name = "customer_email") val customerEmail: String,
    val price: Double,
    val timestamp: LocalDateTime,
)