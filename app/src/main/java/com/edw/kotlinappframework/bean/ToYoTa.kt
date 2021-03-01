package com.edw.kotlinappframework.bean

import javax.inject.Inject
import javax.inject.Qualifier

/**
 * Author: EdwardWMD
 * Data: 2021/3/1
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class ToYoTa @Inject
constructor() : Car {

    override fun speedPerHour(): String {
       return "最高时速:562公里/小时"
    }

    override fun drivenDistance(): String {
       return "已行驶了125634.2公里!"
    }

    fun carCharacteristics():String{
        return "汉兰达,帝王的尊享!!"
    }
}
@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class BindCar