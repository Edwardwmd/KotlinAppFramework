package com.edw.kotlinappframework.db.realm

import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.edw.kotlinappframework.App
import io.realm.Realm
import io.realm.RealmAsyncTask
import io.realm.RealmConfiguration
import io.realm.RealmResults

/**
 * Author: EdwardWMD
 * Data: 2021/3/6
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Realm 数据库管理类
 */
class RealmManager constructor(config: RealmConfiguration):RealmCarDao {
    private var realm: Realm? = null
    private var transaction: RealmAsyncTask? = null

//    companion object {
//        @Volatile
//        private var instance: RealmManager? = null
//
//        fun getInstance(config: RealmConfiguration) = instance ?: synchronized(this) {
//            instance ?: RealmManager(config).apply {
//                instance = this
//            }
//        }
//    }

    init {
        Realm.setDefaultConfiguration(config)
        realm = Realm.getInstance(config)
    }

    //插入多条数据
    override fun insert(CarsList: MutableList<Cars>) {
        Log.e(
            "当前线程---->",
            "线程ID:${Thread.currentThread().id}    是否为主线程:${Looper.getMainLooper().thread == Thread.currentThread()}"
        )
        realm?.apply {
            transaction = executeTransactionAsync({
                Log.e(
                    "当前线程---->",
                    "线程ID:${Thread.currentThread().id}    是否为主线程:${Looper.getMainLooper().thread == Thread.currentThread()}"
                )
                it.insert(CarsList)
            }, {
                Toast.makeText(App.appContext(), "添加数据成功!", Toast.LENGTH_SHORT).show()
            }, {
                Log.e("Realm_Insert_Error", "添加数据错误:${it.message}")

            })
        }

    }

    //查询所有数据
    override fun queryAll(): RealmResults<Cars>? {
        realm!!.refresh()
        return realm!!.where(Cars::class.java).findAllAsync()!!
    }

    //修改数据
    override fun updateData(Cars: Cars) {
        realm?.apply {
            transaction = executeTransactionAsync({
                it.insertOrUpdate(Cars)
            }, {

                Log.e("Realm_Update_Success", "修改数据成功!")
            }, {

                Log.e("Realm_Update_Error", "修改错误:${it.message}")
            })
        }
    }

    //删除所有数据
    override fun deleteAllData() {
        realm?.apply {
            transaction = executeTransactionAsync {
                it.deleteAll()
            }
        }
    }

    //根据车品牌查询
    override fun queryByBrand(brand: String): RealmResults<Cars>? {
        return realm!!.where(Cars::class.java).equalTo("carBrand", brand).findAllAsync()
    }

    //根据车类型查询
    override fun queryByType(type: String): RealmResults<Cars>? {
        return realm!!.where(Cars::class.java).equalTo("carType", type).findAllAsync()
    }

    //根据车品牌删除
    override fun deleteByBrand(brand: String) {
        realm?.apply {
            transaction = executeTransactionAsync {
                it.where(Cars::class.java).equalTo("carBrand", brand).findAll().deleteAllFromRealm()
            }
        }
    }

    //根据车类型删除
    override fun deleteByType(type: String) {
        realm?.apply {
            executeTransactionAsync {
                it.where(Cars::class.java).equalTo("carType", type).findAll().deleteAllFromRealm()
            }
        }
    }


    //取消RealmAsyncTask
    fun cancelTransaction() {
        transaction?.apply {
            if (!isCancelled)
                cancel()
        }
    }

    //关闭数据库
    fun close() {
        if (realm != null)
            realm!!.close()
        realm = null
    }
}