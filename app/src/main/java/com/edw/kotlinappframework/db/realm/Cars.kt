package com.edw.kotlinappframework.db.realm

import io.realm.RealmObject

/**
 * Author: EdwardWMD
 * Data: 2021/3/5
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */

open class Cars(
    open var carBrand: String="",
    open var carName: String="",
    open var carType: String="",
    open var numOfCarSeats: Int=0
): RealmObject()