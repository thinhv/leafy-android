package com.leafy.ui.plantunitdetails

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.leafy.R
import com.leafy.ui.units.UnitsViewModel
import kotlinx.android.synthetic.main.activity_plant_unit_details.*

class PlantUnitDetailsActivity: AppCompatActivity() {
    private val viewModel: UnitsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_unit_details)
        setupObservers()


    }

    private fun setupObservers() {
        viewModel.plantUnits.observe(this, Observer {
            val item = it.data?.find { it -> it.id == intent.getStringExtra("PLANT_UNIT_ID") }
            tempText.text = item?.data?.temp.toString()
            co2Text.text = item?.data?.co2.toString()
            tvocText.text = item?.data?.tvoc.toString()
            hText.text = item?.data?.h.toString()
            if (item?.data?.light == 1) {
                lightText.text = "On"
            } else {
                lightText.text = "Off"
            }
            phText.text = item?.data?.ph.toString()
            ecText.text = item?.data?.ec.toString()
            tempwText.text = item?.data?.tempW.toString()
        })

    }
}