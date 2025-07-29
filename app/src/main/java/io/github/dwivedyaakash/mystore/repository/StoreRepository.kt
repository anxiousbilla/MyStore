package io.github.dwivedyaakash.mystore.repository

import io.github.dwivedyaakash.mystore.model.Product
import io.github.dwivedyaakash.mystore.service.ApiService

class StoreRepository {
    private val apiService = ApiService()

    suspend fun getProducts(): Result<List<Product>> {
        return try {
            val products = apiService.getProducts()
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: String): Result<Product> {
        return try {
            val product = apiService.getProductById(id)
            Result.success(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
