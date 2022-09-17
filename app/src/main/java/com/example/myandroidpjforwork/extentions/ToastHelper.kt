package com.example.myandroidpjforwork.extentions

import android.content.Context
import android.widget.Toast




object ToastHelper {

     var mToast: Toast? = null

    /**
     * 弹出Toast
     * @param context 上下文对象
     * @param text    提示的文本
     * @param duration 持续时间（0：短；1：长）
     */
    fun showToast(context: Context?, text: String?, duration: Int) {
        if (mToast==null) {
            mToast = Toast.makeText(context, text, duration)
        } else {
            mToast?.setText(text)
            mToast?.duration = duration
        }
        mToast?.show()
    }

    /**
     *
     * 弹出Toast
     * @param context 上下文对象
     * @param text    提示的文本
     * @param duration 持续时间（0：短；1：长）
     * @param gravity  位置（Gravity.CENTER;Gravity.TOP;...）
     */
    fun showToast(context: Context?, text: String?, duration: Int, gravity: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration)
        } else {
            mToast?.setText(text)
            mToast?.duration = duration
        }
        mToast?.setGravity(gravity, 0, 0)
        mToast?.show()
    }

    /**
     * 关闭Toast
     */
    fun cancelToast() {
            mToast?.cancel()
    }
}