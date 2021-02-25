package com.edw.kotlinappframework.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

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
}