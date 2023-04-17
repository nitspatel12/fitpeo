package com.fitpeo.assignment.network

import com.fitpeo.assignment.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object{
        private var INSTANCE: Retrofit? = null

        fun getRetrofit():Retrofit{
            if(INSTANCE == null){
                INSTANCE = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }

            return INSTANCE!!
        }
    }
}