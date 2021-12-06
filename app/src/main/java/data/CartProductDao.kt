package data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCartProduct(cartProduct: CartProduct)

    @Query("SELECT * FROM cart_items")
    fun readAllData():LiveData<List<CartProduct>>

    @Query("select count(id) from cart_items")
    fun count():Int
}