package com.edw.kotlinappframework.db.room

/**
 * Author: EdwardWMD
 * Data: 2021/3/3
 * Project: RxRoom
 * Website: https://github.com/Edwardwmd
 * 数据库类,是继承自RoomDatabase的抽象类,里面定义类一些抽象接口类获取的方法
 *
 */
//关于版本迁移:Migration
//当数据库中的表新增字段或者删减字段需要做版本迁移,比如原来的版本(version)是1,就要改成2,下一次字段变更时,版本以此类推的增加.
//我这里原来的版本是2,现在在StudentEntity中新增字段skill.修改版本为3
//注意:想要保留数据的情况下做版本迁移就需要调用addMigrations()方法,这个非常重要.
//@Database(entities = [StudentEntity::class], version = 3, exportSchema = false)
//abstract class StudentDB : RoomDatabase() {
//
//    abstract fun getStudentDao(): StudentDAO
//
//    companion object {
//        //版本迁移:2->3(这个是增加字段下的迁移)
//        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE student_info ADD COLUMN stu_skill TEXT") //在数据库表student_info中添加了一个字段stu_skill
//            }
//        }
//
//        //版本迁移3->4(这个是删减字段下的迁移,过程较复杂),整个步骤如下:
//        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                //1.在旧表的前提下先创建一个删减掉某个字段的新表(我这里包含字段:id,stu_name,stu_sex),去除stu_skill字段;
//                database.execSQL("CREATE TABLE student_info_temp (stu_name TEXT,stu_sex TEXT,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL) ")
//                // 2.然后将旧表student_info的数据转移到新表student_info_temp中(旧表和新表的字段要保持一致)
//                database.execSQL("INSERT INTO student_info_temp (stu_name,stu_sex,id) SELECT stu_name,stu_sex,id FROM student_info")
//                // 3.将旧表student_info删除.
//                database.execSQL("DROP TABLE student_info")
//                //4.将新建的表名命名student_info_temp成旧表的名student_info
//                database.execSQL("ALTER TABLE student_info_temp RENAME TO student_info")
//            }
//
//        }
//
//        //双重检测锁单例初始化StudentDB
//        val INSATANCE: StudentDB by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            Room
//                .databaseBuilder(App.appContext(), StudentDB::class.java, "StudentAllInfo.db")
//                .addMigrations(MIGRATION_2_3)//这个方法是在保留数据的前提下做版本迁移,fallbackToDestructiveMigration()是将原有数据清空在做版本迁移
//                .build()
//        }
//
//    }
//
//}