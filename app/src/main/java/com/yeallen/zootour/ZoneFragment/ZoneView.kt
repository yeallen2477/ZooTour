package com.yeallen.zootour.ZoneFragment

import com.yeallen.zootour.dataFormat.ZoneInfo

interface ZoneView {
    fun selectZone(zoneInfo : ZoneInfo)
}