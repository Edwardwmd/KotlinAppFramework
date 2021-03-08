package com.edw.kotlinappframework.db.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: EdwardWMD
 * Data: 2021/3/3
 * Project: RxRoom
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
@Entity(tableName = "student_info")
data class StudentEntity(
    @ColumnInfo(name = "stu_name")
    val name: String?,
    @ColumnInfo(name = "stu_sex")
    val sex: String?,
    @ColumnInfo(name = "stu_skill")
    val skill:String?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
