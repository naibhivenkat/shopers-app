package com.shopersapp.models

data class Shop(
    val id: Int,
    val name: String,
    val items: List<Item>
)
