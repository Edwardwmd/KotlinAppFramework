package com.edw.rxroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Author: EdwardWMD
 * Data: 2021/3/3
 * Project: RxRoom
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
@Dao
interface StudentDAO {
    //插入多个数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(students: MutableList<StudentEntity>)

    //展示所有数据
    @Query("SELECT * FROM student_info")
    fun queryAll(): LiveData<MutableList<StudentEntity>>

    //删除表中所有数据
    @Query("DELETE FROM student_info")
    suspend fun deleteAll()

    //通过ID修改数据
    @Query("UPDATE student_info SET stu_name=:name,stu_sex=:sex WHERE id=:id")
    suspend fun updateData(id: Long, name: String, sex: String)

    //根据Id删除数据
    @Query("DELETE FROM STUDENT_INFO WHERE id=:id")
    suspend fun deleteById(id: Long)

    //根据姓名删除数据
    @Query("DELETE FROM STUDENT_INFO WHERE stu_name=:name")
    suspend fun deleteByName(name: String)

    //根据性别删除数据
    @Query("DELETE FROM STUDENT_INFO WHERE stu_sex=:sex")
    suspend fun deleteBySex(sex: String)


}