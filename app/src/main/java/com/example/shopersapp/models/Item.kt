package com.example.shopersapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: Int,
    val name: String,
    val price: Double,
    val icon: String
) : Parcelable
