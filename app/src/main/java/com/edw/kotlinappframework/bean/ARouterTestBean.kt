package com.edw.kotlinappframework.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * Author: EdwardWMD
 * Data: 2021/2/22
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
@SuppressLint("ParcelCreator")// 用于处理 Lint 的错误提示
@Parcelize
data class ARouterTestBean(val pName:String,val pAge:Int,val pDesc:String) : Parcelable

