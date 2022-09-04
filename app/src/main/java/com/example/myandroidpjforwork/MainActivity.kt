package com.example.myandroidpjforwork

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TabHost
import androidx.appcompat.app.AppCompatActivity
import com.example.myandroidpjforwork.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



    }


}