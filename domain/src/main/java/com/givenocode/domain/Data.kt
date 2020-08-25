package com.givenocode.domain

data class Data(
    val content: String,
    val timestamp: Long,
    val source: Source
) {

    sealed class Source {
        object ONLINE: Source()
        object OFFLINE: Source()
    }
}