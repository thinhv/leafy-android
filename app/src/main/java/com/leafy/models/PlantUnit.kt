package com.leafy.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class PlantUnit(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("data")
    val data: Map<Any, Any>,
    @field:SerializedName("publishTime")
    val publishTime: String
)