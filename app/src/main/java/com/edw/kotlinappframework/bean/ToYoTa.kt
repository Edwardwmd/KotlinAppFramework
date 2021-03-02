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
constructor(val speed: Double, val distance: Double, private val char: String) : Car {

    override fun speedPerHour(): String {
        return "最高时速:${speed}公里/小时"
    }

    override fun drivenDistance(): String {
        return "已行驶了${distance}公里!"
    }

    fun carCharacteristics(): String {
        return char
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class BindCar