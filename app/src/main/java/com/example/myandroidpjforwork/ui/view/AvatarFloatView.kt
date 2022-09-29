package com.example.myandroidpjforwork.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.myandroidpjforwork.R
import com.example.myandroidpjforwork.databinding.ViewLoginTipsBinding
import com.google.android.material.imageview.ShapeableImageView

class AvatarFloatView(context: Context) : BaseFloatView(context) {

    private var mAdsorbType = ADSORB_VERTICAL

    override fun getChildView(): View {
        val binding = ViewLoginTipsBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun getIsCanDrag(): Boolean {
        return true
    }

    override fun getAdsorbType(): Int {
        return mAdsorbType
    }

    fun setAdsorbType(type: Int) {
        mAdsorbType = type
    }
}