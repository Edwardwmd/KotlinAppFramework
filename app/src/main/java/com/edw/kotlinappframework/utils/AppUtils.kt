package com.edw.kotlinappframework.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Looper

/**
 * Author: EdwardWMD
 * Data: 2021/2/16
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: 工具类
 */
object AppUtils {
    fun turnToActivity(mC: Context, clazz: Class<Any>, isFinish: Boolean = false) {
        val intent = Intent()
        intent.setClass(mC, clazz)
        mC.startActivity(intent)
        if (isFinish)
            (mC as Activity).finish()
    }

    /**
     * 判断线程是否在主线程上
     */
     fun isMainThread(): String =
        if (Looper.getMainLooper().thread == Thread.currentThread()) "是" else "不是"

}