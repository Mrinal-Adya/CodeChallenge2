package data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CartProductViewModelFactory(private val repositry:CartProductRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartProductViewModel(repositry) as T
    }
}