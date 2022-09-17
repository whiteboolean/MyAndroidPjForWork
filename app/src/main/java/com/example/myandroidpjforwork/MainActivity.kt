package com.example.myandroidpjforwork

import android.os.Bundle
import android.provider.Contacts.Intents.UI
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myandroidpjforwork.databinding.ActivityMainBinding
import com.example.myandroidpjforwork.extentions.*
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.math.max


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val tabLayout by lazy { binding.tabLayout }
    private val viewpager2 by lazy { binding.viewPagerContent }

    @OptIn(FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pagerAdapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.viewpager_content, parent, false)
                Log.d("TextAdpter:", "onCreateViewHolder")
                return MyViewHolder(view)
            }

            override fun getItemCount(): Int {
                return 2
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            }

            inner class MyViewHolder(val item: View) : ViewHolder(item) {

            }

        }
        viewpager2.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            tab.text = "第${position}个Tab"
        }.attach()


        binding.btnGood.setOnClickListener {
            ToastUtil.showToast("你好")
        }

        /**
         *1. 发言频率
         */
        val speakFrequencyTime = 10000L
        binding.btnGood.clickFlow()
            .throttleFirst(speakFrequencyTime, true)
            .onEach {
                ToastUtil.showToast("整挺好：$it")
            }.launchIn(lifecycleScope)

        /**
         * 2.单条消息连续数字长度
         */
        val singleMessageLength = 3
        val numberStr = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')

        val strTest = "单挑消息长度3432解封了都是3243放到进料口2"

        fun getConstantsNumberLength(str: String) {
            var cl = 0
            var maxLength = 0
            strTest.forEach {
                if (numberStr.contains(it)) {
                    cl++
                } else {
                    cl = 0
                }
                maxLength = maxLength.coerceAtLeast(cl)
            }

        }


    }


}

