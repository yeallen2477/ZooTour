package com.yeallen.zootour

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import antonioleiva.com.appkotlin.login.MainPresenter
import com.yeallen.zootour.Main.MainInteractor
import com.yeallen.zootour.Main.MainView
import com.yeallen.zootour.PlantFragment.PlantFragment
import com.yeallen.zootour.PlantFragment.PlantView
import com.yeallen.zootour.ZoneFragment.ZoneFragment
import com.yeallen.zootour.ZoneFragment.ZoneView
import com.yeallen.zootour.ZoneInfoFragment.ZoneInfoFragment
import com.yeallen.zootour.ZoneInfoFragment.ZoneInfoView
import com.yeallen.zootour.dataFormat.Plant
import com.yeallen.zootour.dataFormat.ResultPlant
import com.yeallen.zootour.dataFormat.ResultZone
import com.yeallen.zootour.dataFormat.ZoneInfo
import com.yeallen.zootour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , MainView, ZoneView, ZoneInfoView, PlantView {
    var binding: ActivityMainBinding? = null

    private val presenter = MainPresenter(this, MainInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())

        binding!!.btnGet.setOnClickListener {
            if (isDonwloadFinish) {
                getBack()
            }
        }
    }


    var handler : Handler = Handler()
    override fun onError() {
        Toast.makeText(this, "下載資料失敗,稍後自動重試.", Toast.LENGTH_SHORT).show()

        handler.postDelayed(retryRunnable, 5000)
    }

    private val retryRunnable = Runnable {
        presenter.downloadData(this@MainActivity)
    }

    private lateinit var zoneFragment:ZoneFragment
    private lateinit var zoneInfoFragment:ZoneInfoFragment
    private var zoneInfoTitle : String = ""
    private lateinit var plantFragment: PlantFragment
    override fun navigateToHome(resultZoneList: ResultZone, resultPlantList: ResultPlant) {
        isDonwloadFinish = true
        binding?.rlProgress?.visibility = View.GONE
        zoneFragment = ZoneFragment(this, resultZoneList, resultPlantList)

        addFragment(zoneFragment)
        replaceFragment(zoneFragment)
    }

    private fun addFragment(f: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.rlFragmentContainer, f)
        transaction.commit()
    }

    private fun replaceFragment(f : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rlFragmentContainer, f)
        transaction.commit()
    }

    @SuppressLint("NewApi")
    override fun selectZone(zoneInfo : ZoneInfo) {
        binding?.btnGet?.background = getDrawable(R.drawable.arrow_left)
        binding?.tvMainTitle?.text = zoneInfo.getZone().getName()
        zoneInfoTitle = zoneInfo.getZone().getName()
        zoneInfoFragment = ZoneInfoFragment(this, zoneInfo)
        addFragment(zoneInfoFragment)
        replaceFragment(zoneInfoFragment)
    }

    @SuppressLint("NewApi")
    override fun selectPlant(plant: Plant) {
        binding?.btnGet?.background = getDrawable(R.drawable.arrow_left)
        binding?.tvMainTitle?.text = plant.getNameCh()
        plantFragment = PlantFragment(this, plant)
        addFragment(plantFragment)
        replaceFragment(plantFragment)
    }

    var isDonwload: Boolean = false
    var isDonwloadFinish: Boolean = false

    @SuppressLint("NewApi")
    fun getBack() {
        if (!isDonwload){
            presenter.downloadData(this@MainActivity)
            isDonwload = true
        } else if (zoneFragment.isVisible) {

        } else if (zoneInfoFragment.isVisible) {
            binding?.btnGet?.background = getDrawable(R.drawable.home)
            binding?.tvMainTitle?.text = "台北市立動物園"
            replaceFragment(zoneFragment)
        } else if (plantFragment.isVisible) {
            binding?.btnGet?.background = getDrawable(R.drawable.arrow_left)
            binding?.tvMainTitle?.text = zoneInfoTitle
            replaceFragment(zoneInfoFragment)
        } else {

        }
    }

    override fun back() {

    }

    override fun onResume() {
        super.onResume()

        val connManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        if (mWifi!!.isConnected) {
            if (!isDonwload) {
                getBack()
            }
        } else {
            Toast.makeText(this, "請確認您的網路連線", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(retryRunnable)
    }
}