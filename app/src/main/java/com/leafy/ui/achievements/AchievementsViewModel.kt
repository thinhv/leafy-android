package com.leafy.ui.achievements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leafy.models.PlantUnit
import com.leafy.repository.PlantUnitsRepository
import com.leafy.repository.PlantUnitsRepositoryImpl
import com.leafy.repository.Resource

class AchievementsViewModel: ViewModel() {
    val plantUnits: LiveData<Resource<List<PlantUnit>>>

    private val repository: PlantUnitsRepository = PlantUnitsRepositoryImpl()

    init {
        plantUnits = repository.loadPlantUnits()
    }
}