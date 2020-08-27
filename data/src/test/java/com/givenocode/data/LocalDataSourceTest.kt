package com.givenocode.data

import kotlinx.coroutines.runBlocking
import org.junit.Test

class LocalDataSourceTest {

    @Test
    fun `getData always returns something`() = runBlocking {
        val localDataSource = LocalDataSource()

        repeat(100) {
            assert(localDataSource.getData().isNotBlank())
        }
    }

    @Test
    fun `getData always returns different values`() = runBlocking {
        val localDataSource = LocalDataSource()

        val resultsSet = mutableSetOf<String>()

        repeat(100) {
            resultsSet.add(localDataSource.getData())
        }

        assert(resultsSet.size > 1)
    }
}