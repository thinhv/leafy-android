package com.leafy.network

import androidx.lifecycle.LiveData
import com.leafy.models.PlantUnit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://us-central1-amiable-hydra-279814.cloudfunctions.net/mikrokosmos/api/read"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(LiveDataCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface LeafyApiService {
    @GET("/")
    fun getPlantUnits(): LiveData<ApiResponse<List<PlantUnit>>>
}

object ClientApi {
    val retrofitService: LeafyApiService by lazy {
        retrofit.create(LeafyApiService::class.java)
    }
}