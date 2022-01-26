package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class ResultsZone {
    @SerializedName(value = "count")
    private val count: String = ""

    @SerializedName(value = "results")
    private val mZoneList: List<Zone?>? = null

    fun getCount(): String {
        return count
    }

    fun getZoneList(): List<Zone?>? {
        return mZoneList
    }
}