package com.example.myandroidpjforwork.day202209

import com.example.myandroidpjforwork.extentions.log
import com.example.myandroidpjforwork.extentions.logN
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {


    /**
     * Flow基本使用
     */
//    runBlocking {
//        flow {
//            for (i in 0..10) {
//                emit("$i")
//            }
//        }.onStart {
//            println("start:$this")
//        }.onEach {
//            println("onEach = it:$it")
//        }.onCompletion {
//            println("onCompletion:$this")
//        }.collect()
//    }


    /**
     * Flow retry
     */
    runBlocking {
        flow {
            for (i in 0..10) {
                delay(100)
                if (i == 3) throw RuntimeException("自定义错误")
                emit(i)
            }
        }.retry(2) //再试两次 一共三次
            .onStart { logN("onStart") }
            .onEach {
                logN("it:$it")
            }.onCompletion {
                logN("competition:$this")
            }.catch {exception->
                logN("catch:$exception")
            }.collect()
    }

}