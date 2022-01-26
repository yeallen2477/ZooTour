package com.yeallen.zootour.utils

import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.util.Pair
import androidx.annotation.RequiresApi
import com.yeallen.zootour.Main.MainInteractor
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HttpDownloadTask(var context: Context, var listener: HttpDownloadResponse) :
    AsyncTask<String?, Void?, Pair<String?, String?>>() {
    private val TAG = "HomePlus-MemberActivity"
    private var timeout = 3 * 1000

    interface HttpDownloadResponse {
        fun HttpDownloadResponse(result: Pair<String?, String?>?)
    }

    fun setTimeout(timeout: Int) {
        this.timeout = timeout
    }

    //var httpDownloadResponse: HttpDownloadResponse


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun doInBackground(vararg params: String?): Pair<String?, String?> {
        Log.d(TAG, "doInBackground")
        var out = ""
        //if (params[0].regionMatches(0, "https://", 0, 8)) {
        out = downloadHttp(params[0], params[1])
        //}
        Log.d(TAG, "out = $out")
        val result: Pair<String?, String?> = Pair<String?, String?>(
            params[1], out
        )
        return result
    }

    fun onProgressUpdate(vararg a: Void) {
        super.onProgressUpdate()
    }

    override fun onPostExecute(result: Pair<String?, String?>) {
        // myParseXmlEPG(result.second);
        // MainActivity.channelDownloadTables.remove(channelNumber);
        //httpDownloadResponse.HttpDownloadResponse(result)

        // myParseXmlEPG(result.second);
        // MainActivity.channelDownloadTables.remove(channelNumber);
        listener.HttpDownloadResponse(result)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun downloadHttp(sUrl: String?, mode: String?): String {
        return try {
            Log.d(TAG, sUrl!!)
            val url = URL(sUrl)
            val c = url.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.connectTimeout = timeout
            c.readTimeout = timeout
            c.useCaches = false
            c.connect()

            val stringBuilder = StringBuilder()
            var line: String? = null
            try {
                val respCode = c.responseCode
                if (respCode == 200) {
                    val reader = BufferedReader(InputStreamReader(c.inputStream))
                    while (reader.readLine().also { line = it } != null) {
                        stringBuilder.append(
                            """
                            $line
                            """.trimIndent()
                        )
                    }
                } else {
                    stringBuilder.append("Error  - " + respCode + " " + c.responseMessage)
                }
            } finally {
                c.disconnect()
            }

            return stringBuilder.toString()
        } catch (e: MalformedURLException) {
            "URLError: $e"
        } catch (e: IOException) {
            "IOError:$e"
        }
    }

    init {
        //httpDownloadResponse = context as HttpDownloadResponse
    }

}