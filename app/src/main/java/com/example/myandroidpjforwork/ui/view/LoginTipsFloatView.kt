package com.example.myandroidpjforwork.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.example.myandroidpjforwork.R
import com.example.myandroidpjforwork.databinding.ViewLoginTipsBinding
import com.google.android.material.imageview.ShapeableImageView

class LoginTipsFloatView(context: Context) : BaseFloatView(context) {

    private var mAdsorbType = ADSORB_VERTICAL

    lateinit var button5: Button
    var onButtonClick: (() -> Unit)? = null

    override fun getChildView(): View {
        val binding = ViewLoginTipsBinding.inflate(LayoutInflater.from(context))
        button5 = binding.button5
        button5.setOnClickListener {
            onButtonClick?.invoke()
        }
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