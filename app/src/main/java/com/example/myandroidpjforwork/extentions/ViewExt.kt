package com.example.myandroidpjforwork.extentions

import android.content.Context
import android.widget.Toast
import com.example.myandroidpjforwork.App

fun showToast(context: Context, str:String){
    ToastHelper.showToast(context,str,Toast.LENGTH_LONG)
}