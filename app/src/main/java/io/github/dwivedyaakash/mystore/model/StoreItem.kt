package io.github.dwivedyaakash.mystore.model

data class StoreItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)