package com.yeallen.zootour.Main

import android.content.Context
import android.util.Log
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import com.yeallen.zootour.utils.HttpDownloadTask
import com.google.gson.Gson
import com.yeallen.zootour.dataFormat.ResultPlant
import com.yeallen.zootour.dataFormat.ResultZone
import java.lang.Exception


class MainInteractor : HttpDownloadTask.HttpDownloadResponse {

    interface OnLoginFinishedListener {
        fun onError()
        fun onSuccess(resultZoneList: ResultZone, resultPlantList: ResultPlant)
    }

    private var httpDownloadTask: HttpDownloadTask? = null
    private lateinit var context: Context
    private lateinit var listener: OnLoginFinishedListener

    fun downloadData(context: Context, listener: OnLoginFinishedListener) {
        this.context = context
        this.listener = listener
        httpDownloadTask = HttpDownloadTask(context, this)
        httpDownloadTask!!.execute(
            "https://data.taipei/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire",
            "1"
        )
    }

    fun downloadDataPlant(context: Context) {
        httpDownloadTask = HttpDownloadTask(context, this)
        httpDownloadTask!!.execute(
            "https://data.taipei/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire",
            "2"
        )
    }

    lateinit var resultList: ResultZone
    override fun HttpDownloadResponse(result: Pair<String?, String?>?) {
        Log.d("TAG", "result?.second?"+result?.second)
        Log.d("TAG", "result?.second?"+(result?.second?.indexOf("Error") != -1))
        if (result?.first == "1") {
            if (result?.second?.indexOf("Error") != -1) {
                listener.onError()
            } else {
                resultList = getResultZone(result?.second)!!;

                downloadDataPlant(this.context);
            }
        } else if (result?.first == "2") {
            if (result?.second?.indexOf("Error") != -1) {
                listener.onError()
            } else {
                var resultPlantList: ResultPlant? = getResultPlant(result?.second);

                if (resultList != null && resultPlantList != null) {
                    resultPlantList?.getResultsPlant()?.getPlantList()?.forEach {
                        Log.d("test", it?.getNameCh().toString());
                        Log.d("test", it?.getNameEn().toString());
                    }

                    listener.onSuccess(resultList, resultPlantList)
                }
            }
        }
    }

    private fun getResultZone(result: String?): ResultZone? {
        var resultList: ResultZone? = null
        try {
            val gson = Gson()
            resultList = gson.fromJson(result, ResultZone::class.java)
        } catch (unused: Exception) {
            Log.d("test", "unused " + unused)
        }
        return resultList
    }

    private fun getResultPlant(result: String?): ResultPlant? {
        var resultList: ResultPlant? = null
        try {
            val gson = Gson()
            resultList = gson.fromJson(result, ResultPlant::class.java)
        } catch (unused: Exception) {
            Log.d("test", "unused " + unused)
        }
        return resultList
    }
}