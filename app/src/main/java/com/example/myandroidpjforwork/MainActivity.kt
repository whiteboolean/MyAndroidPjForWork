package com.example.myandroidpjforwork

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myandroidpjforwork.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val tabLayout by lazy { binding.tabLayout }
    private val viewpager2 by lazy { binding.viewPagerContent }

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


    }


}