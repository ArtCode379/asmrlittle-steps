package asmrtraders.babycare.asmrlittlesteps.di

import androidx.room.Room
import asmrtraders.babycare.asmrlittlesteps.data.database.GCZCTDatabase
import org.koin.dsl.module

private const val DB_NAME = "gczct_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = GCZCTDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<GCZCTDatabase>().cartItemDao() }

    single { get<GCZCTDatabase>().orderDao() }
}