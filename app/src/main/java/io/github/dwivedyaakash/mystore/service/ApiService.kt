package io.github.dwivedyaakash.mystore.service

import io.github.dwivedyaakash.mystore.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiService {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                encodeDefaults = false
            })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }

    private val baseUrl = "https://fakestoreapi.com"

    suspend fun getProducts(): List<Product> {
        return client.get("$baseUrl/products").body()
    }

    suspend fun getProductById(id: String): Product {
        return client.get("$baseUrl/products/$id").body()
    }

    fun close() {
        client.close()
    }
}
