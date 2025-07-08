package com.example.shopersapp.network

import com.example.shopersapp.models.LoginRequest
import com.example.shopersapp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
>
