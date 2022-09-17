package com.example.myandroidpjforwork.extentions

import java.util.regex.Pattern

object NumberUtils {


    fun getConstantNumberLength(str: String): Int {
        var cl = 0
        var maxLength = 0
        str.forEach {
            if (it.isNumeric()) cl++ else cl = 0
            maxLength = maxLength.coerceAtLeast(cl)
        }
        return maxLength
    }


    /**
     *判断 [str] 是否是纯数字
     */
    fun getIfFullNumericMsg(str: String): Boolean {
        if (!str.isNumeric()) {
            return false
        }
        return str.isNotEmpty()
    }

    /**
     * 点前后字母个数
     *
     */
    fun getNumericSize(str: String) {
        val point = '.'
        val pointIndex = str.indexOf(point)

    }

    /**
     * 是否是Url
     */

    /**
     * 判断字符串是否为URL
     *
     * @param urls 需要判断的String类型url
     * @return true:是URL；false:不是URL
     */
    fun isHttpUrl(urls: String): Boolean {
        //设置正则表达式
        val regex = ("(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)")
        //对比
        val pat = Pattern.compile(regex.trim { it <= ' ' })
        val mat = pat.matcher(urls.trim { it <= ' ' })
        return mat.matches()
    }


    /**
     * 判断字符是否是数字
     */
    private fun <T> T.isNumeric(): Boolean {
        val pattern: Pattern = Pattern.compile("\\d*")
        return pattern.matcher(this.toString()).matches()
    }
}
