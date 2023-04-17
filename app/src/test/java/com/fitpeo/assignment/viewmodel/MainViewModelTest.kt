package com.fitpeo.assignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fitpeo.assignment.model.data.PhotoModelList
import com.fitpeo.assignment.repository.PhotoRepository
import com.fitpeo.assignment.utils.NetworkResult

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Rule
import org.mockito.Mockito

class MainViewModelTest {

    lateinit var dispatcher: TestDispatcher

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: PhotoRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun test_GetPhotos() = runTest{
        val pages = 1
        Mockito.`when`(repository.fetchPhotos(pages)).thenReturn(NetworkResult.Success(
            PhotoModelList()
        ))
        val sut = MainViewModel(repository)
        sut.fetchPhotos()
        dispatcher.scheduler.advanceUntilIdle()
        val result = sut.photos.value
        Assert.assertEquals(0, result!!.data?.size)

    }

    @Test
    fun test_GetPhotos_expectedError() = runTest {
        val pages = 1
        Mockito.`when`(repository.fetchPhotos(pages)).thenReturn(NetworkResult.Error(
            "Something went wrong"
        ))
        val sut = MainViewModel(repository)
        sut.fetchPhotos()
        dispatcher.scheduler.advanceUntilIdle()
        val result = sut.photos.value
        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong", result?.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}