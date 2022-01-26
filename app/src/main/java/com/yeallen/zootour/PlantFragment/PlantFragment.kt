package com.yeallen.zootour.PlantFragment

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
import com.yeallen.zootour.dataFormat.Plant
import com.yeallen.zootour.dataFormat.ResultZone
import com.yeallen.zootour.dataFormat.ZoneInfo
import com.yeallen.zootour.databinding.FragmentLevel1Binding
import com.yeallen.zootour.databinding.FragmentLevel2Binding
import com.yeallen.zootour.databinding.FragmentMainBinding
import java.util.*


class PlantFragment(var plantView: PlantView?, var plant : Plant?) : Fragment() {
    private lateinit var binding: FragmentLevel2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLevel2Binding.inflate(inflater)
        val rootView: View = binding!!.getRoot()

        initLayout()

        return rootView
    }

    private fun initLayout() {
        Picasso.get()
            .load(plant?.getPic01URL())
            .placeholder(R.drawable.loading)
            .fit()
            .into(binding!!.ivPic)
        binding.tvTitle.text = plant?.getNameCh()
        binding.tvSubTitle.text = plant?.getNameEn()
        binding.tvItem1.text = plant?.getAlsoKnown()
        binding.tvItem2.text = plant?.getBrief()
        binding.tvItem3.text = plant?.getFeature()
        binding.tvItem4.text = plant?.getFunctionApplication()
        binding.tvItem5.text = plant?.getUpdate()
    }
}