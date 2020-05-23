package com.example.searchsuggestions.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

object AppUtils {
    fun isConnected(context: Context): Boolean {
        val connection =
            (context.getSystemService("connectivity") as ConnectivityManager).activeNetworkInfo
        return if (connection == null || !connection.isConnectedOrConnecting) {
            false
        } else true
    }

    fun showToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}