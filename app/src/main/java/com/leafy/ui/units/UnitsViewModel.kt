package com.leafy.ui.units

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leafy.models.PlantUnit
import com.leafy.repository.PlantUnitsRepository
import com.leafy.repository.PlantUnitsRepositoryImpl
import com.leafy.repository.Resource

class UnitsViewModel: ViewModel() {
    val plantUnits: LiveData<Resource<List<PlantUnit>>>

    private val repository: PlantUnitsRepository = PlantUnitsRepositoryImpl()

    init {
        plantUnits = repository.loadPlantUnits()
    }
}