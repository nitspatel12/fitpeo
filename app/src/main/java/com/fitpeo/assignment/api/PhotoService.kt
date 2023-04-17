package com.fitpeo.assignment.api

import com.fitpeo.assignment.model.data.PhotoModelList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    suspend fun fetchPhotos(@Query("_page") page : Int): Response<PhotoModelList>
}