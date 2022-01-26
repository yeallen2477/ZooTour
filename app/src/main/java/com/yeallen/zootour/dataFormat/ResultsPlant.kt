package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class ResultsPlant {
    @SerializedName(value = "count")
    private val count: String = ""

    @SerializedName(value = "results")
    private val mPlantList: List<Plant?>? = null

    fun getCount(): String {
        return count
    }

    fun getPlantList(): List<Plant?>? {
        return mPlantList
    }
}