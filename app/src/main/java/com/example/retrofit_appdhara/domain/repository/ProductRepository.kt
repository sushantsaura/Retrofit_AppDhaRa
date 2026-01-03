package com.example.retrofit_appdhara.domain.repository

import com.example.retrofit_appdhara.data.remoteapi.ProductApiService
import com.example.retrofit_appdhara.domain.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api : ProductApiService
) {

    suspend fun getAllProducts() = api.getProducts()

    suspend fun getProduct(id : Int) = api.getProduct(id)

    suspend fun createProduct(product: Product) = api.createProduct(product)

    suspend fun updateProduct(id : Int, product: Product) = api.updateProduct(id, product)

    suspend fun patchProduct(id : Int, updates : Map<String, Any>) = api.patchProduct(id, updates)

    suspend fun deleteProduct(id : Int ) = api.deletedProduct(id)
}