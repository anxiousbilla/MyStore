package io.github.dwivedyaakash.mystore.repository

import io.github.dwivedyaakash.mystore.model.StoreResponse
import io.github.dwivedyaakash.mystore.service.ApiService

class StoreRepository {
    private val apiService = ApiService()

    suspend fun getProducts(): Result<StoreResponse> {
        return try {
            val products = apiService.getProducts()
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
