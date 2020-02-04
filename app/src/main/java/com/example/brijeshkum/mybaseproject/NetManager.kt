package com.example.brijeshkum.mybaseproject

import android.content.Context
import android.net.ConnectivityManager

class NetManager(private val context: Context) {
    val isConnected: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo.isConnectedOrConnecting
        }

}