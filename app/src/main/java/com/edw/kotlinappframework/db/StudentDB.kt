package com.edw.rxroom

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edw.kotlinappframework.App

/**
 * Author: EdwardWMD
 * Data: 2021/3/3
 * Project: RxRoom
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
@Database(entities = [StudentEntity::class], version = 2, exportSchema = false)
abstract class StudentDB : RoomDatabase() {
    abstract fun getStudentDao(): StudentDAO

    companion object {
        val INSATANCE: StudentDB by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Room
                .databaseBuilder(App.appContext(), StudentDB::class.java, "StudentAllInfo.db")
                .build()
        }
    }


}