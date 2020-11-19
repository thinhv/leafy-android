package com.leafy.network

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://leafy-backend.herokuapp.com/graphql"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(LiveDataCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface LeafyApiService {
    /*
    @GET("/")
    fun getPosts(): LiveData<ApiResponse<List<..>>>
    */
}

object ClientApi {
    val retrofitService: LeafyApiService by lazy {
        retrofit.create(LeafyApiService::class.java)
    }
}