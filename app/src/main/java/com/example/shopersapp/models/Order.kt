package com.example.shopersapp.app.models

data class Order(
    val id: Int,
    val customerId: Int,
    val shopId: Int,
    val customerName: String,
    val items: List<Item>,
    val status: String
)
