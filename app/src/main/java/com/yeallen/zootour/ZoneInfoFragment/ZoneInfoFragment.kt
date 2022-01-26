package com.yeallen.zootour.ZoneInfoFragment

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.yeallen.zootour.R
import com.yeallen.zootour.dataFormat.ResultZone
import com.yeallen.zootour.dataFormat.ZoneInfo
import com.yeallen.zootour.databinding.FragmentLevel1Binding
import com.yeallen.zootour.databinding.FragmentMainBinding
import java.util.*


class ZoneInfoFragment(var zoneInfoView: ZoneInfoView?, var zoneInfo : ZoneInfo?) : Fragment() {
    private lateinit var binding: FragmentLevel1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLevel1Binding.inflate(inflater)
        val rootView: View = binding!!.getRoot()

        binding.lvMain
        initLayout()

        return rootView
    }

    private fun initLayout() {
        var zone = zoneInfo?.getZone()
        activity?.let { Glide.with(it).load(zone?.getPicURL()).placeholder(R.drawable.loading).centerCrop().into(binding!!.ivPic) }
        binding.tvInfo.text = zone?.getInfo()
        binding.tvSubItem.text = zone?.getMemo()
        binding.tvSubItem2.text = zone?.getCategory()
        //binding.tvSubItem3.autoLinkMask = Linkify.WEB_URLS;
        binding.tvSubItem3.movementMethod = LinkMovementMethod.getInstance()
        binding.tvSubItem3.text = Html.fromHtml("<a href=\'"+ zone?.getURL() +"\'>在網頁開啟</a>")

        binding.lvMain.setAdapter(getCountryListAdapter())
        binding.lvMain.setOnItemClickListener { parent, view, position, id ->
            zoneInfo?.getPlantList()?.get(position)?.let { zoneInfoView?.selectPlant(it) }
        }
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
                Log.d("TAG", ""+value)
                var url = value?.replace("\"", "")
                if (url != null && view != null) {
                    activity?.let { Glide.with(it).load(url).placeholder(R.drawable.loading).into(view) }
                };
            }
        }
    }

    private fun getZoneMapList(): ArrayList<Map<String, String>> {
        val platformSet: MutableSet<String> = HashSet()
        val timeList: ArrayList<Map<String, String>> = ArrayList()
        var map:HashMap<String, String>
        zoneInfo?.getPlantList()?.forEach { plant ->
            map = HashMap<String, String>()
            plant?.let { map.put("item_icon", it.getPic01URL()) }
            plant?.let { map.put("item_title", it.getNameCh()) }
            plant?.let { map.put("item_info", it.getAlsoKnown()) }
            map.put("item_tvInfo2", "")
            timeList.add(map)
        }
        return timeList
    }
}