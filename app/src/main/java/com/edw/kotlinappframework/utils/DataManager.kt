package com.edw.kotlinappframework.utils

import com.edw.kotlinappframework.adapter.HiltItem
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
        ButtonData("#ff6600", "Hilt Dagger依赖注入"),
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
    val hiltDatas = mutableListOf(
        HiltItem("语文", 54, "望庐山瀑布"),
        HiltItem("语文", 54, "望庐山瀑布"),
        HiltItem("语文", 22, "静夜思"),
        HiltItem("数学", 78, "线性代数"),
        HiltItem("编程", 45, "C语言"),
        HiltItem("计算机原理", 186, "CPU指令集"),
        HiltItem("数学", 105, "函数的定义"),
        HiltItem("英语", 114, "现在进行时的学习"),
        HiltItem("编程", 128, "Java面向对象思想"),
        HiltItem("编程", 187, "Kotlin的协程运用"),
    )
}