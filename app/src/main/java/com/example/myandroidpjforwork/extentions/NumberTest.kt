package com.example.myandroidpjforwork.extentions

import java.util.regex.Pattern


fun main() {


    val strTest = "11111"

    fun getConstantNumberLength(str: String): Int {
        var cl = 0
        var maxLength = 0
        str.forEach {
            if (isNumeric(it.toString())) {
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

/**
 * 判断[str]字符是否是数字
 */
fun isNumeric(str: String): Boolean {
    val pattern: Pattern = Pattern.compile("\\d*")
    return pattern.matcher(str).matches()
}