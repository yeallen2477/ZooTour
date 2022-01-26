package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class ResultPlant {
    @SerializedName(value = "result")
    private val mResultsPlant: ResultsPlant? = null

    fun getResultsPlant(): ResultsPlant? {
        return mResultsPlant
    }
}