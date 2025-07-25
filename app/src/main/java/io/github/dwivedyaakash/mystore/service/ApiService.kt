package io.github.dwivedyaakash.mystore.service

import io.github.dwivedyaakash.mystore.model.StoreResponse
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
            })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }

    private val baseUrl = "https://fakestoreapi.com"

    suspend fun getProducts(): StoreResponse {
        return client.get("$baseUrl/products").body()
    }

    fun close() {
        client.close()
    }
}
