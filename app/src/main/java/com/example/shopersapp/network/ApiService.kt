package com.example.shopersapp.network

import com.example.shopersapp.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("shops")
    fun getShops(): Call<List<Shop>>

    @GET("shop/{id}/items")
    fun fetchItems(@Path("id") shopId: Int): Call<List<Item>>

    @POST("orders")
    fun placeOrder(@Body order: Order): Call<Void>

    @GET("orders")
    fun fetchOrders(): Call<List<Order>>

    @POST("orders/{id}/complete")
    fun markOrderComplete(@Path("id") orderId: Int): Call<Void>

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    @POST("orders/complete")
    fun completeOrder(@Body orderId: Int): Call<Void>

    @GET("items/{shopId}")
    fun getItems(@Path("shopId") shopId: Int): Call<List<Item>>

    @POST("place_order")
    fun placeOrder(@Body body: PlaceOrderRequest): Call<Void>

}
