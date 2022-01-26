package antonioleiva.com.appkotlin.login

import android.content.Context
import com.yeallen.zootour.Main.MainInteractor
import com.yeallen.zootour.Main.MainView
import com.yeallen.zootour.dataFormat.ResultPlant
import com.yeallen.zootour.dataFormat.ResultZone
import com.yeallen.zootour.utils.HttpDownloadTask

class MainPresenter(var loginView: MainView?, val loginInteractor: MainInteractor) :
    MainInteractor.OnLoginFinishedListener {

    fun downloadData(context: Context) {
        loginInteractor.downloadData(context, this)
    }

    fun onDestroy() {
        loginView = null
    }

    override fun onError() {
        loginView?.onError()
    }

    override fun onSuccess(resultZoneList: ResultZone, resultPlantList: ResultPlant) {
        loginView?.navigateToHome(resultZoneList, resultPlantList)
    }
}