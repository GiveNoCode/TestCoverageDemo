package com.givenocode.testcoveragedemo

import com.givenocode.domain.DataInteractor
import com.givenocode.domain.DataSource
import com.givenocode.domain.DeviceManager

object SimpleDependencyInjector {

    private val dataSource by lazy {
        object : DataSource {
            override suspend fun getData(): String {
                return "stub data"
            }
        }
    }

    private val deviceManager by lazy {
        object : DeviceManager {
            override fun getTimeStamp(): Long {
                return System.currentTimeMillis()
            }

            override fun isOnline(): Boolean {
                return true
            }
        }
    }

    val dataInteractor by lazy {
        DataInteractor(dataSource, dataSource, deviceManager)
    }
}