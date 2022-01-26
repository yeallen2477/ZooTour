package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class ZoneInfo {
    private lateinit var mZone : Zone

    private lateinit var mPlantList: ArrayList<Plant>

    fun setInfo(mZone : Zone, mPlantList : ArrayList<Plant>) {
        this.mZone = mZone
        this.mPlantList = mPlantList
    }

    fun getZone(): Zone{
        return mZone
    }

    fun getPlantList(): ArrayList<Plant> {
        return mPlantList
    }

}