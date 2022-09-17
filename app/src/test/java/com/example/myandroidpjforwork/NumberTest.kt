package com.example.myandroidpjforwork

import kotlin.math.max


fun main() {

    val singleMessageLength = 3
    val numberStr = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')

    val strTest = "单挑消息长度34 3 2解封fds32范德萨范德萨了都是放到进料口2"


    fun getConstantNumberLength(str: String) :Int{
        var cl = 0
        var maxLength = 0
        str.forEach {
            if (numberStr.contains(it)) {
                cl++
            } else {
                cl = 0
            }
            maxLength = maxLength.coerceAtLeast(cl)
        }
        return maxLength

    }

    print(getConstantNumberLength(strTest))


}