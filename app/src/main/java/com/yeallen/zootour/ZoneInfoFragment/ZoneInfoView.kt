package com.yeallen.zootour.ZoneInfoFragment

import com.yeallen.zootour.dataFormat.Plant
import com.yeallen.zootour.dataFormat.ZoneInfo

interface ZoneInfoView {
    fun selectPlant(plant : Plant)
}