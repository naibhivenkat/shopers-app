package com.example.shopersapp.network

data class RegisterRequest(
    val username: String,
    val password: String,
    val role: String
)
