package com.edw.kotlinappframework.utils

import com.edw.kotlinappframework.adapter.HiltItem
import com.edw.kotlinappframework.bean.ButtonData
import com.edw.kotlinappframework.bean.GlideItem
import com.edw.kotlinappframework.db.StudentEntity

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
        ButtonData("#cc6633", "Room数据库")
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
    val studentData = mutableListOf(
        StudentEntity("张三丰", "男","太极拳"),
        StudentEntity("张无忌", "男","乾坤大挪移"),
        StudentEntity("赵敏", "女","无"),
        StudentEntity("黄蓉", "女","打狗棒法"),
        StudentEntity("杨过", "男","黯然销魂掌"),
        StudentEntity("杨康", "男","全真派心法"),
        StudentEntity("穆念慈", "女","无"),
        StudentEntity("小昭", "女","无"),
        StudentEntity("乔峰", "男","降龙十八掌"),
        StudentEntity("段誉", "男","凌波微步"),
        StudentEntity("虚竹", "男","小无相功"),
        StudentEntity("李秋水", "女","天山六阳掌")
    )
}