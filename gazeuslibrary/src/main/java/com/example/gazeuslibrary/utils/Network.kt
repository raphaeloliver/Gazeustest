package com.example.gazeuslibrary.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Network {

    fun availableNetwork(context: Context): Boolean {

        var connectResult = false

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = connectivityManager.activeNetwork ?: return false
            val netWorkAction =
                connectivityManager.getNetworkCapabilities(capabilities) ?: return false
            connectResult = when {
                netWorkAction.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                netWorkAction.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                netWorkAction.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
                netWorkAction.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager?.run {
                connectivityManager.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        connectResult = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        connectResult = true
                    }
                }
            }
        }
        return connectResult
    }
}