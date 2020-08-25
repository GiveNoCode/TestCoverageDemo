package com.givenocode.domain

interface DataSource {

    suspend fun getData(): String
}