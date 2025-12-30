package com.example.retrofit_appdhara.data.remoteapi

import com.example.retrofit_appdhara.domain.model.Product
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApiService {

    @GET("products")
    suspend fun getProducts() : List<Product>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id : Int) : Product

    @POST("products")
    suspend fun createProduct(@Body product: Product) : Product

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id : Int , @Body product: Product) : Product

    @PATCH("products/{id}")
    suspend fun patchProduct(@Path("id") id : Int, @Body updates : Map<String,Any>) : Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id : Int ) : Product


}