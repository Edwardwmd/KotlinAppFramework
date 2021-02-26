package com.edw.kotlinappframework.utils

import com.edw.kotlinappframework.bean.ButtonData
import com.edw.kotlinappframework.bean.GlideItem

/**
 * Author: EdwardWMD
 * Data: 2021/2/20
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
object DataManager {
    val buttonDatas = mutableListOf(
        ButtonData("#ff00cc", "glide图片加载"),
        ButtonData("#cc00cc", "Retrofit网络请求"),
        ButtonData("#9900cc", "Rxjava简单使用"),
        ButtonData("#6600cc", "ARouter简单使用"),
        ButtonData("#3366cc", "EventBus简单使用"),
        ButtonData("#FF34B3", "Koin依赖注入"),
        ButtonData("#ff6600", "glide图片加载"),
        ButtonData("#cc6633", "glide图片加载"),
        ButtonData("#669933", "glide图片加载"),
        ButtonData("#ffff33", "glide图片加载"),
        ButtonData("#FF4500", "glide图片加载"),
        ButtonData("#96CDCD", "glide图片加载"),
        ButtonData("#9BCD9B", "glide图片加载"),
        ButtonData("#B22222", "glide图片加载"),
        ButtonData("#66CDAA", "glide图片加载"),
        ButtonData("#53868B", "glide图片加载"),
        ButtonData("#4A708B", "glide图片加载"),
        ButtonData("#2F4F4F", "glide图片加载"),
        ButtonData("#00868B", "glide图片加载"),

        )
    val glideItems = mutableListOf(
        GlideItem(ConstantUtil.PIC_URL, "标准模式"),
        GlideItem(ConstantUtil.PIC_URL, "全圆角模式"),
        GlideItem(ConstantUtil.PIC_URL1, "占位符模式"),
        GlideItem(ConstantUtil.PIC_URL2, "错误符模式"),
        GlideItem(ConstantUtil.PIC_URL3, "回调符模式"),
        GlideItem(ConstantUtil.PIC_URL3, "缩略图模式"),
    )
}