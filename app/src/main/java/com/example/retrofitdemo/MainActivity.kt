package com.example.retrofitdemo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_description.*
import retrofit.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launchWhenCreated {

            findViewById<ProgressBar>(R.id.progressBar).isVisible = true
            val response = try {
                RetrofitInstance.api.getProducts()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection1")
                findViewById<ProgressBar>(R.id.progressBar).isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                findViewById<ProgressBar>(R.id.progressBar).isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                Toast.makeText(this@MainActivity, "Response successful", Toast.LENGTH_SHORT).show()
                productAdapter.products = response.body()!!
            } else {
                Toast.makeText(this@MainActivity, "Response not successful", Toast.LENGTH_SHORT).show()
            }
            findViewById<ProgressBar>(R.id.progressBar).isVisible = false
        }

        findViewById<RecyclerView>(R.id.rvProducts).apply {
            productAdapter = ProductAdapter()
            adapter = productAdapter
            layoutManager  = LinearLayoutManager(context)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this,CartDetails::class.java)
             startActivity(intent)
        }
    }
}