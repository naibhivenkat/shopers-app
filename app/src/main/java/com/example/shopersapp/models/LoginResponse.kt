package com.example.shopersapp.models

data class LoginResponse(
    val success: Boolean,
    val user_type: String,  // ← Make sure this matches your API response
    val user_id: Int
)
