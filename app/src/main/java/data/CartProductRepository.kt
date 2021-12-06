package data

class CartProductRepository(private val db:CartProductDatabase) {

    suspend fun addCartProduct(cartProduct: CartProduct) = db.cartProductDao().addCartProduct(cartProduct)
    fun readAllData() = db.cartProductDao().readAllData()
    fun count() = db.cartProductDao().count()
}