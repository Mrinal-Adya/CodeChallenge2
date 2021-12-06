package com.example.retrofitdemo

import android.content.*
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import data.*
import kotlinx.android.synthetic.main.product_card_view.*
import kotlinx.android.synthetic.main.product_card_view.productTitle
import kotlinx.android.synthetic.main.product_description.*
import retrofit.Product
import retrofit.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class ProductDescription: AppCompatActivity() {


    private var products : List<Product>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_description)
         val bundle = intent.extras
         val position= (bundle!!.getString("positionid"))

        lifecycleScope.launchWhenCreated {

            findViewById<ProgressBar>(R.id.progressBar).isVisible = true
            val response = try {
                RetrofitInstance.api.getProducts()
            } catch(e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection1")
                findViewById<ProgressBar>(R.id.progressBar).isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpException, unexpected response")
                findViewById<ProgressBar>(R.id.progressBar).isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                Toast.makeText(this@ProductDescription, "Response successful", Toast.LENGTH_SHORT).show()

                    products = response.body()!!
                    initializeValues(products!!, position1)

            } else {
                Toast.makeText(this@ProductDescription, "Response not successful", Toast.LENGTH_SHORT).show()
            }
            findViewById<ProgressBar>(R.id.progressBar).isVisible = false
        }

        addToCart.setOnClickListener {
            val productRepository = CartProductRepository(CartProductDatabase(this))
            val factory = CartProductViewModelFactory(productRepository)
            val viewModel = ViewModelProvider(this, factory).get(CartProductViewModel::class.java)
            val cartProduct = CartProduct(
                products!![position1].category,products!![position1].description,products!![position1].id,
                products!![position1].image,products!![position1].price,products!![position1].rating.rate,products!![position1].title)
            viewModel.addCartProduct(cartProduct)
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()

        }

    }

    private fun initializeValues(products: List<Product>, position: Int) {
        Log.e("description", position1.toString())
        productTitle.text = products[position1].title
        productRating.rating = products[position1].rating.rate
        productDescription.text = products[position1].description
        Picasso.get().load(products[position1].image).into(productImage1)
    }
}