package com.example.retrofitdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.databinding.CartCardViewBinding
import com.example.retrofitdemo.databinding.CartViewBinding
import com.squareup.picasso.Picasso
import data.CartProduct
import data.CartProductViewModel
import kotlinx.android.synthetic.main.cart_card_view.view.*

class CartProductAdapter(var cartProduct: List<CartProduct>, val viewModel:CartProductViewModel):RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>(){

    var amount: Double = 0.0
    inner class CartProductViewHolder(binding: CartCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        return CartProductViewHolder(CartCardViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return cartProduct.size
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {

        val product = cartProduct[position]
        Log.e("check",position.toString())
        Picasso.get().load(product.image).into(holder.itemView.productImage)
        holder.itemView.productTitle.text = product.title
        holder.itemView.productPrice.text = product.price.toString()
        amount += product.price

    }
}