package com.yeallen.zootour.Main

import com.yeallen.zootour.dataFormat.ResultPlant
import com.yeallen.zootour.dataFormat.ResultZone

interface MainView {
    fun onError()
    fun navigateToHome(resultZoneList: ResultZone, resultPlantList: ResultPlant)
}