package com.givenocode.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DataInteractorTest {

    @MockK
    private lateinit var localDataSource: DataSource

    @MockK
    private lateinit var onlineDataSource: DataSource

    @MockK
    private lateinit var deviceManager: DeviceManager

    @InjectMockKs
    private lateinit var dataInteractor: DataInteractor

    @Before
    fun setUp() =
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun `happy case`() = runBlocking {
        // given
        every { deviceManager.isOnline() } returns true
        every { deviceManager.getTimeStamp() } returns 12345

        coEvery { onlineDataSource.getData() } returns "some online data"

        // when
        val result = dataInteractor.getData()

        // then
        assertEquals("some online data", result.content)
        assertEquals(12345, result.timestamp)
        assertEquals(Data.Source.ONLINE, result.source)

        coVerify(exactly = 1) { onlineDataSource.getData() }
        coVerify(exactly = 0) { localDataSource.getData() }
    }

    @Test
    fun `offline case`() = runBlocking {
        // given
        every { deviceManager.isOnline() } returns false
        every { deviceManager.getTimeStamp() } returns 12345

        coEvery { localDataSource.getData() } returns "some offline data"

        // when
        val result = dataInteractor.getData()

        // then
        assertEquals("some offline data", result.content)
        assertEquals(12345, result.timestamp)
        assertEquals(Data.Source.OFFLINE, result.source)

        coVerify(exactly = 0) { onlineDataSource.getData() }
        coVerify(exactly = 1) { localDataSource.getData() }
    }

    @Test
    fun `network exception case`() = runBlocking {
        // given
        every { deviceManager.isOnline() } returns true
        every { deviceManager.getTimeStamp() } returns 12345

        coEvery { onlineDataSource.getData() } throws IOException("network exception")
        coEvery { localDataSource.getData() } returns "some offline data"

        // when
        val result = dataInteractor.getData()

        // then
        assertEquals("some offline data", result.content)
        assertEquals(12345, result.timestamp)
        assertEquals(Data.Source.OFFLINE, result.source)

        coVerify(exactly = 1) { onlineDataSource.getData() }
        coVerify(exactly = 1) { localDataSource.getData() }
    }


}