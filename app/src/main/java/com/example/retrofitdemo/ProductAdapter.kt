package com.example.retrofitdemo

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.databinding.ProductCardViewBinding
import com.squareup.picasso.Picasso
import retrofit.Product
import kotlin.properties.Delegates

var position1 by Delegates.notNull<Int>()

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {



    inner class ProductViewHolder(val binding: ProductCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var products: List<Product>
        get() = differ.currentList
        set(value) { differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        return ProductViewHolder(ProductCardViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
       holder.binding.apply {
           val product = products[position]
           Log.e("check",position.toString())
           Picasso.get().load(product.image).into(productImage)
           productTitle.text = product.title
           productPrice.text = "$ "+ product.price.toString()
           position1 = position

           holder.itemView.setOnClickListener {
               val intent = Intent(holder.itemView.context,ProductDescription::class.java)
               intent.putExtra("positionid", position1)
               Log.e("check1",position.toString())
               holder.itemView.context.startActivity(intent)
              // LocalBroadcastManager.getInstance(holder.itemView.context).sendBroadcast(intent);
           }
       }
    }

    override fun getItemCount(): Int {
       return products.size
    }

}