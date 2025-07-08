package com.example.shopersapp.models

data class Shop(
    val id: Int,
    val name: String,
    val location: String,
    val items: List<Item>
)
