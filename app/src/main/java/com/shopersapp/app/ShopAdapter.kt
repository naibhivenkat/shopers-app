package com.example.shopersapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.shopersapp.R
import com.example.shopersapp.models.Shop

class ShopAdapter(private val context: Context, private val shops: List<Shop>) : BaseAdapter() {

    override fun getCount(): Int = shops.size
    override fun getItem(position: Int): Any = shops[position]
    override fun getItemId(position: Int): Long = shops[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val shop = shops[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.shop_item, parent, false)

        val shopName = view.findViewById<TextView>(R.id.shop_name)
        val shopLocation = view.findViewById<TextView>(R.id.shop_location)

        shopName.text = shop.name
        shopLocation.text = shop.location

        return view
    }
}
