package com.yeallen.zootour.ZoneFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.yeallen.zootour.R
import com.yeallen.zootour.dataFormat.Plant
import com.yeallen.zootour.dataFormat.ResultPlant
import com.yeallen.zootour.dataFormat.ResultZone
import com.yeallen.zootour.dataFormat.ZoneInfo
import com.yeallen.zootour.databinding.FragmentMainBinding
import java.util.*
import kotlin.collections.ArrayList


class ZoneFragment(var zoneView: ZoneView?, val zoneList: ResultZone, val plantList: ResultPlant) :
    Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = LayoutInflater.from(context).inflate(R.layout.activity_main, container, false)
        //v.text_view.text = "Fragment $index"
        binding = FragmentMainBinding.inflate(inflater)
        val rootView: View = binding!!.getRoot()

        binding.lvMain
        initLayout()

        return rootView
    }

    private fun initLayout() {

        //lvDate.setAdapter(getTimeListAdapter());
        binding.lvMain.setAdapter(getCountryListAdapter())
        binding.lvMain.setOnItemClickListener { parent, view, position, id ->
            zoneView?.selectZone(getZooInfo(position))
        }
    }

    fun getZooInfo(position: Int): ZoneInfo {
        var zoneInfo: ZoneInfo
        zoneInfo = ZoneInfo()
        var zone = zoneList.getResultsZone()?.getZoneList()?.get(position)
        var plantList : ArrayList<Plant> = getPlantList(zone!!.getName())
        zoneInfo.setInfo(zone, plantList)
        return zoneInfo
    }

    fun getPlantList(location : String): ArrayList<Plant> {
        var plantList: ArrayList<Plant> = ArrayList<Plant>()
        this.plantList.getResultsPlant()?.getPlantList()?.forEach { plant ->
            if (plant?.getLocation()?.indexOf(location) != -1) {
                plant?.let { plantList.add(it) }
            }
        }

        return plantList
    }

    private fun getCountryListAdapter(): SimpleAdapter? {
        return object : SimpleAdapter(
            activity,
            getZoneMapList(),
            R.layout.list_view_item,
            arrayOf("item_icon", "item_title", "item_info", "item_tvInfo2"),
            intArrayOf(R.id.ivIcon, R.id.tvTitle, R.id.tvInfo, R.id.ivInfo2)
        ) {
            override fun setViewImage(view: ImageView?, value: String?) {

                // here you put code to take your "value" string
                // and get your image, for example using Picasso
                // or Glide, and putting it in the "v" ImageView.
                Log.d("TAG", "" + value)
                var url = value?.replace("\"", "")
                if (view != null) {
                    //activity?.let { Glide.with(it).load(url).into(view) }
                    activity?.let {
                        Picasso.get()
                            .load(url)
                            .placeholder(R.drawable.loading)
                            .resize(100, 100)
                            .centerCrop()
                            .into(view)
                    }
                };
            }
        }
    }

    //lateinit var map: MutableMap<String, String>
    private fun getZoneMapList(): ArrayList<Map<String, String>> {
        val platformSet: MutableSet<String> = HashSet()
        //if (null != zoneList) for (zone in zoneList) {
        //Log.d("TESTA", "??? "+site.getCounty());
        //platformSet.add(zone.getPicURL())
        //}
        Log.d("TESTA", "!!! " + platformSet.size)
        val timeList: ArrayList<Map<String, String>> = ArrayList()
        //map = HashMap()
        var map: HashMap<String, String>
        zoneList.getResultsZone()?.getZoneList()?.forEach { zone ->
            map = HashMap<String, String>()
            zone?.let { map.put("item_icon", it.getPicURL()) }
            zone?.let { map.put("item_title", it.getName()) }
            zone?.let { map.put("item_info", it.getInfo()) }
            zone?.let { map.put("item_tvInfo2", it.getMemo()) }
            timeList.add(map)
        }
        return timeList
    }
}