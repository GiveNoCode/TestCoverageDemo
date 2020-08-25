package com.givenocode.domain

import java.io.IOException

class DataInteractor (
    private val localDataSource: DataSource,
    private val onlineDataSource: DataSource,
    private val deviceManager: DeviceManager
) {

     suspend fun getData(): Data {
        val timestamp = deviceManager.getTimeStamp()

        return  if (deviceManager.isOnline()) {
            try {
                Data(onlineDataSource.getData(), timestamp, Data.Source.ONLINE)
            } catch (e: IOException) {
                Data(localDataSource.getData(), timestamp, Data.Source.OFFLINE)
            }
        } else {
            Data(localDataSource.getData(), timestamp, Data.Source.OFFLINE)
        }
    }
}