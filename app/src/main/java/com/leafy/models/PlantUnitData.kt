package com.leafy.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class PlantUnitData(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("h")
    val h: Float,
    @field:SerializedName("ec")
    val ec: Float,
    @field:SerializedName("temp")
    val temp: Float,
    @field:SerializedName("time")
    val time: String,
    @field:SerializedName("tempW")
    val tempW: Float,
    @field:SerializedName("light")
    val light: Int,
    @field:SerializedName("ph")
    val ph: Float,
    @field:SerializedName("tvoc")
    val tvoc: Float,
    @field:SerializedName("co2")
    val co2: Int
)