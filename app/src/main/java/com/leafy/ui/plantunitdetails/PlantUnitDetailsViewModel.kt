package com.leafy.ui.plantunitdetails

import androidx.lifecycle.ViewModel
import com.leafy.repository.PlantUnitsRepository
import com.leafy.repository.PlantUnitsRepositoryImpl

class PlantUnitDetailsViewModel: ViewModel() {
    private val plantUnitsRepository: PlantUnitsRepository = PlantUnitsRepositoryImpl()
}