package com.shopers.app.models

data class Order(
    val id: Int,
    val customerId: Int,
    val shopId: Int,
    val customerName: String,
    val items: List<Item>,
    val status: String
)
