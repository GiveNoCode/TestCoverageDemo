package com.givenocode.data

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class NetworkDataSourceTest {

    val mockWebServer = MockWebServer()
    val networkDataSource = NetworkDataSource()

    @Before
    fun setUp() {
        mockWebServer.start()
        networkDataSource.setUrl(mockWebServer.url("").toString())
    }

    @After
    fun tearDown(){
        mockWebServer.close()
    }

    @Test
    fun `happy case`() = runBlocking {
        // given
        mockWebServer.enqueue(MockResponse().setBody("some text"))

        // when
        val result = networkDataSource.getData()

        // then
        assertEquals("some text", result)
    }

    @Test
    fun `ignore html tags`() = runBlocking {
        // given
        mockWebServer.enqueue(MockResponse().setBody("<p>some text</p>"))

        // when
        val result = networkDataSource.getData()

        // then
        assertEquals("some text", result)
    }

    @Test
    fun `request failed`() = runBlocking {
        // given
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        try {
            // when
            val result = networkDataSource.getData()

            // then
            fail("should fail")
        } catch (e: IOException) {
            assertEquals("Network Error", e.message)
        }
    }
}