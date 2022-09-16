package com.example.myandroidpjforwork.day202209

import com.example.myandroidpjforwork.extentions.log
import com.example.myandroidpjforwork.extentions.logN
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 *  https://www.jb51.net/article/261296.htm
 */
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
//    runBlocking {
//        flow {
//            for (i in 0..10) {
//                delay(100)
//                if (i == 3) throw RuntimeException("自定义错误")
//                emit(i)
//            }
//        }.retry(2) //再试两次 一共三次
//            .onStart { logN("onStart") }
//            .onEach {
//                logN("it:$it")
//            }.onCompletion {
//                logN("competition:$this")
//            }.catch {exception->
//                logN("catch:$exception")
//            }.collect()
//    }


    /**
     * Flow withTimeOut
     */
    val handlerException = CoroutineExceptionHandler { coroutineContext, throwable ->
        log("$coroutineContext $throwable")
    }
//    runBlocking(handlerException) {
//        withTimeout(200) {
//            flow {
//                for (i in 0..10) {
//                    delay(100)
//                    if (i == 5) throw RuntimeException("自定义错误")
//                    emit("$i")
//                }
//            }.retry(3)
//                .onEach {
//                    logN("it:$it")
//                }.onCompletion {
//                    logN("competition:$this, $it")
//                }.catch { exception ->
//                    logN("catch:$exception")
//                }.collect()
//        }
//    }

    val flow = (1..8).asFlow().onStart {
        logN("onStart")
    }.onCompletion {
        logN("competition:$this, $it")
    }.catch { exception ->
        logN("catch:$exception")
    }

    runBlocking {
        //Flow的取消依赖协程的取消
        withTimeoutOrNull(210) {
            flow.collect {
                logN("collect:$it")
            }
        }
    }

}