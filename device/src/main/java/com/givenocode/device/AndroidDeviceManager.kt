package com.givenocode.device

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.givenocode.domain.DeviceManager

class AndroidDeviceManager (private val context: Context) : DeviceManager {

    override fun getTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    override fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}