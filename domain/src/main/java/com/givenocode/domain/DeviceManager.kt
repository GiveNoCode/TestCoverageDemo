package com.givenocode.domain

import com.sun.org.apache.xpath.internal.operations.Bool

interface DeviceManager {

    fun getTimeStamp(): Long

    fun isOnline(): Boolean
}