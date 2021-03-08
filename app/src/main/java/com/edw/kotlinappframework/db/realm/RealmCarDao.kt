package com.edw.kotlinappframework.db.realm

import io.realm.RealmResults

/**
 * Author: EdwardWMD
 * Data: 2021/3/8
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
interface RealmCarDao {
    fun insert(CarsList:MutableList<Cars>)

    fun queryAll(): RealmResults<Cars>?

    fun updateData(Cars: Cars)

    fun deleteAllData()

    fun queryByBrand(brand:String): RealmResults<Cars>?

    fun queryByType(type: String): RealmResults<Cars>?

    fun deleteByBrand(brand: String)

    fun deleteByType(type:String)


}