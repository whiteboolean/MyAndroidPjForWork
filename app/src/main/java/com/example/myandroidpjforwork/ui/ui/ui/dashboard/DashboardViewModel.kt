package com.example.myandroidpjforwork.ui.ui.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


    private val _list = MutableLiveData<MutableList<String>>().apply {
        value =  mutableListOf(
            "A",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
            "B",
        )
    }
    val list :MutableLiveData<MutableList<String>> = _list

}