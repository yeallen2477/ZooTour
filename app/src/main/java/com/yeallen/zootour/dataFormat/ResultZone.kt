package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class ResultZone {
    @SerializedName(value = "result")
    private val ResultsZone: ResultsZone? = null

    fun getResultsZone(): ResultsZone? {
        return ResultsZone
    }
}