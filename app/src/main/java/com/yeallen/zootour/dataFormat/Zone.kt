package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class Zone {
    @SerializedName(value = "E_Pic_URL")
    private val mPicURL: String = ""

    @SerializedName(value = "E_Info")
    private val mInfo: String = ""

    @SerializedName(value = "E_Category")
    private val mCategory: String = ""

    @SerializedName(value = "E_Name")
    private val mName: String = ""

    @SerializedName(value = "E_Memo")
    private var mMemo: String = ""

    @SerializedName(value = "E_URL")
    private val mURL: String = ""

    fun getPicURL(): String {
        return mPicURL
    }

    fun getInfo(): String {
        return mInfo
    }

    fun getCategory(): String {
        return mCategory
    }

    fun getName(): String {
        return mName
    }

    fun getMemo(): String {
        if (mMemo.equals(""))
            mMemo = "無休館資訊"
        return mMemo
    }

    fun getURL(): String {
        return mURL
    }
}