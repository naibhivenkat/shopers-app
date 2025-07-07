package com.shopersapp.network

data class LoginResponse(
    val status: String,
    val user_id: Int,
    val username: String,
    val role: String
)
