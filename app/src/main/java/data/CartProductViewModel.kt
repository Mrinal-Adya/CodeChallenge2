package data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartProductViewModel(private val repository: CartProductRepository ):ViewModel() {

    fun addCartProduct(cartProduct: CartProduct) = GlobalScope.launch {
        repository.addCartProduct(cartProduct)
    }

    fun readAllData() = repository.readAllData()
    fun count() = repository.count()
}