package com.example.myandroidpjforwork

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.myandroidpjforwork.bean.Person
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        //注册koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }


    }

    val appModule = module {
        //里面添加各种注入对象
        factory{
            Person()
        }
    }

}