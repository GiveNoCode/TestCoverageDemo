package com.givenocode.testcoveragedemo

import android.content.Context
import com.givenocode.data.LocalDataSource
import com.givenocode.data.NetworkDataSource
import com.givenocode.device.AndroidDeviceManager
import com.givenocode.domain.DataInteractor
import com.givenocode.domain.DeviceManager

object SimpleDependencyInjector {

    lateinit var context: Context

    private val networkDataSource by lazy {
        NetworkDataSource()
    }

    private val localDataSource by lazy {
        LocalDataSource()
    }

    private val deviceManager: DeviceManager by lazy {
        AndroidDeviceManager(context)
    }

    val dataInteractor by lazy {
        DataInteractor(localDataSource, networkDataSource, deviceManager)
    }
}