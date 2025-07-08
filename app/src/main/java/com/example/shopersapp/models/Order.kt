package com.example.shopersapp.models

data class Order(
    val id: Int,
    val customerName: String,
    val items: List<Item>
)
