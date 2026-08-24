package asmrtraders.babycare.asmrlittlesteps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import asmrtraders.babycare.asmrlittlesteps.data.dao.CartItemDao
import asmrtraders.babycare.asmrlittlesteps.data.dao.OrderDao
import asmrtraders.babycare.asmrlittlesteps.data.database.converter.Converters
import asmrtraders.babycare.asmrlittlesteps.data.entity.CartItemEntity
import asmrtraders.babycare.asmrlittlesteps.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GCZCTDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}