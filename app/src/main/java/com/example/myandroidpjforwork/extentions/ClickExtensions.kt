package com.example.myandroidpjforwork.extentions

import android.view.View
import android.widget.Toast
import com.example.myandroidpjforwork.App
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.coroutineContext

fun <T> Flow<T>.throttleFirst(
    thresholdMillis: Long,
    showToast: Boolean = false,
    toastContent: String = "太快了，挖槽"
): Flow<T> = flow {
    var lastTime = 0L // 上次发射数据的时间
    // 收集数据
    collect {
        // 当前时间
        val currentTime = System.currentTimeMillis()
        // 时间差超过阈值则发送数据并记录时间
        if (currentTime - lastTime > thresholdMillis) {
            lastTime = currentTime
            emit(it)
        } else {
            if (showToast) ToastUtil.showToast(toastContent)
        }
    }
}

fun View.clickFlow() = callbackFlow {
    setOnClickListener { trySend(Unit).isSuccess }
    awaitClose { setOnClickListener(null) }
}

