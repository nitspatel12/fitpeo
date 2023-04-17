package com.fitpeo.assignment.repository

import com.fitpeo.assignment.api.PhotoService
import com.fitpeo.assignment.model.data.PhotoModelList
import com.fitpeo.assignment.model.data.PhotoModelListItem
import com.fitpeo.assignment.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PhotoRepositoryTest {

    @Mock
    lateinit var photoService: PhotoService

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetPhotos_EmptyList() = runTest{
        Mockito.`when`(photoService.fetchPhotos(1)).thenReturn(Response.success(PhotoModelList()))

        val sut = PhotoRepository(photoService)
        val result = sut.fetchPhotos(1)
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(0,result.data!!.size)
    }

    @Test
    fun testGetPhotos_expectedPhotoList() = runTest{
        val photoModelList = PhotoModelList()
        photoModelList.add(PhotoModelListItem(1,1,"https://www.google.com","title","https://www.google.com"))
        photoModelList.add(PhotoModelListItem(1,2,"https://www.google.com","title","https://www.google.com"))
        photoModelList.add(PhotoModelListItem(1,3,"https://www.google.com","title","https://www.google.com"))

        Mockito.`when`(photoService.fetchPhotos(1)).thenReturn(Response.success(photoModelList))

        val sut = PhotoRepository(photoService)
        val result = sut.fetchPhotos(1)
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(3,result.data!!.size)
        Assert.assertEquals(2, result.data!![1].id)
    }

    @Test
    fun testGetPhotos_expectedError() = runTest{
        Mockito.`when`(photoService.fetchPhotos(1)).thenReturn(Response.error(401,"Unauthorized".toResponseBody()))

        val sut = PhotoRepository(photoService)
        val result = sut.fetchPhotos(1)
        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong",result.message)
    }
}