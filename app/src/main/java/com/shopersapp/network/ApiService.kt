package com.shopersapp.network

import com.shopersapp.models.Shop
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("/get_shops")
    fun getShops(): Call<List<Shop>>
}
