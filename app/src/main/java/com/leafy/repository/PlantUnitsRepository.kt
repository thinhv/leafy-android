package com.leafy.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leafy.models.PlantUnit
import com.leafy.network.*

interface PlantUnitsRepository {
    fun loadPlantUnits(): LiveData<Resource<List<PlantUnit>>>
}

class PlantUnitsRepositoryImpl(
    private val leafyApiService: LeafyApiService = ClientApi.retrofitService,
) : PlantUnitsRepository {
    override fun loadPlantUnits(): LiveData<Resource<List<PlantUnit>>> {
        return object : NetworkBoundResource<List<PlantUnit>, List<PlantUnit>>() {
            override fun saveCallResult(item: List<PlantUnit>) {
                // Do nothing for now
            }

            override fun shouldFetch(data: List<PlantUnit>?): Boolean {
                // Fetch all for now
                return true
            }

            override fun loadFromDb(): LiveData<List<PlantUnit>> {
                return MutableLiveData<List<PlantUnit>>(listOf<PlantUnit>())
            }

            override fun createCall(): LiveData<ApiResponse<List<PlantUnit>>> {
                return leafyApiService.getPlantUnits()
            }
        }.asLiveData()
    }
}