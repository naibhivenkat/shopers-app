package com.example.shopersapp.network

import com.example.shopersapp.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("shops")
    fun getShops(): Call<List<Shop>>

    @GET("shops/{id}/items")
    fun getItems(@Path("id") shopId: Int): Call<List<Item>>

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    @POST("orders/{id}/complete")
    fun completeOrder(@Path("id") orderId: Int): Call<Void>
}
