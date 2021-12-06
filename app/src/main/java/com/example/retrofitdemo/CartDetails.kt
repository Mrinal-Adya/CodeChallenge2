package com.example.retrofitdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.*
import kotlinx.android.synthetic.main.cart_view.*

class CartDetails:AppCompatActivity() {

    private lateinit var cartProductAdapter: CartProductAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_view)

        val productRepository = CartProductRepository(CartProductDatabase(this))
        val factory = CartProductViewModelFactory(productRepository)
        val  viewModel = ViewModelProvider(this, factory).get(CartProductViewModel::class.java)

            findViewById<RecyclerView>(R.id.rvCartProduct).apply {
             cartProductAdapter = CartProductAdapter(listOf(), viewModel)
            adapter = cartProductAdapter
            layoutManager  = LinearLayoutManager(context)

        }
        viewModel.readAllData().observe(this, Observer {
            cartProductAdapter.cartProduct = it
            cartProductAdapter.notifyDataSetChanged()
        })

        finalPrice.text = "Final Price" + cartProductAdapter.amount


    }
}

