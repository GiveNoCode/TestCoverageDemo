package com.givenocode.testcoveragedemo

import android.content.Context
import com.givenocode.device.AndroidDeviceManager
import com.givenocode.domain.DataInteractor
import com.givenocode.domain.DataSource
import com.givenocode.domain.DeviceManager

object SimpleDependencyInjector {

    lateinit var context: Context

    private val dataSource by lazy {
        object : DataSource {
            override suspend fun getData(): String {
                return "stub data"
            }
        }
    }

    private val deviceManager:DeviceManager by lazy {
        AndroidDeviceManager(context)
    }

    val dataInteractor by lazy {
        DataInteractor(dataSource, dataSource, deviceManager)
    }
}