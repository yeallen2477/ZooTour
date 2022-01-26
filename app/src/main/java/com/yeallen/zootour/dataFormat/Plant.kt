package com.yeallen.zootour.dataFormat

import com.google.gson.annotations.SerializedName

class Plant {
    @SerializedName(value = "F_Name_Latin")
    private val mNameLatin: String = ""

    @SerializedName(value = "F_Location")
    private val mLocation: String = ""

    @SerializedName(value = "F_Pic01_URL")
    private val mPic01URL: String = ""

    @SerializedName(value = "F_Name_Ch")
    private val mNameCh: String = "None"

    @SerializedName(value = "F_AlsoKnown")
    private val mAlsoKnown: String = ""

    @SerializedName(value = "F_Name_En")
    private val mNameEn: String = ""

    @SerializedName(value = "F_Brief")
    private val mBrief: String = ""

    @SerializedName(value = "F_Feature")
    private val mFeature: String = ""

    @SerializedName(value = "F_Function＆Application")
    private val mFunctionApplication: String = ""

    @SerializedName(value = "F_Update")
    private val mUpdate: String = ""

    fun getNameLatin(): String {
        return mNameLatin
    }

    fun getLocation(): String {
        return mLocation
    }

    fun getPic01URL(): String {
        return mPic01URL
    }

    fun getNameCh(): String {
        return mNameCh
    }

    fun getAlsoKnown(): String {
        return mAlsoKnown
    }

    fun getNameEn(): String {
        return mNameEn
    }

    fun getBrief(): String {
        return mBrief
    }

    fun getFeature(): String {
        return mFeature
    }

    fun getFunctionApplication(): String {
        return mFunctionApplication
    }

    fun getUpdate(): String {
        return mUpdate
    }
}