package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartProduct::class],version = 1,exportSchema = false)
 abstract class CartProductDatabase:RoomDatabase() {

     abstract fun cartProductDao() : CartProductDao

    companion object {
        @Volatile
        private var instance: CartProductDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, CartProductDatabase::class.java, "CartProductDatabase.db").build()
    }
}