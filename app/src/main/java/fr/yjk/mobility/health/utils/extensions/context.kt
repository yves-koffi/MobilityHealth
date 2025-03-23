package fr.yjk.mobility.health.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.isOnline(): Boolean {
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
    return networkInfo?.isConnected == true
}


