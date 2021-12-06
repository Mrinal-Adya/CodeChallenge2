package retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/products")
    suspend fun getProducts():Response<List<Product>>
}