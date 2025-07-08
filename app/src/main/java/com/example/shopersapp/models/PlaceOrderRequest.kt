package com.example.shopersapp.models

data class PlaceOrderRequest(
    val shopId: Int,
    val items: List<Item>
)
