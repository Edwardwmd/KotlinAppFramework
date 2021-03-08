package com.edw.kotlinappframework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.adapter.RealmShowAdapter
import com.edw.kotlinappframework.databinding.ActivityRealmDatabaseBinding
import com.edw.kotlinappframework.db.realm.Cars
import com.edw.kotlinappframework.db.realm.RealmManager
import com.edw.kotlinappframework.utils.ConstantUtil
import com.edw.kotlinappframework.utils.DataManager
import io.realm.RealmChangeListener
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope


@Route(path = ConstantUtil.REALM_DATABASE_ACTIVITY_URI)
class RealmDatabaseActivity : AppCompatActivity(), KoinScopeComponent {
    override val scope: Scope by lazy { newScope(this) }
    private val realmManager: RealmManager by scope.inject()
    private val adapter: RealmShowAdapter by scope.inject()
    private var vb: ActivityRealmDatabaseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityRealmDatabaseBinding.inflate(layoutInflater)
        vb?.apply {
            setContentView(root)
            recyRealm.setHasFixedSize(true)
            recyRealm.layoutManager = LinearLayoutManager(this@RealmDatabaseActivity)
            recyRealm.adapter = adapter
            btnInsertRealm.setOnClickListener {
                insertMultiData()
            }

            btnDeleteAll.setOnClickListener {
                deleteAll()
            }

            btnUpdateRealm.setOnClickListener {
                updateData()
            }

            btnDeleteByBrand.setOnClickListener {
                delByBrand()
            }

            btnDeleteType.setOnClickListener {
                delByType()
            }

            btnQueryByBrand.setOnClickListener {
                queryByBrand()
            }

            btnQueryByType.setOnClickListener {
                queryByType()
            }

        }


    }

    private fun queryByType() {
        realmManager.queryByType("大型SUV")?.addChangeListener(RealmChangeListener {
            adapter.setDatas(it)
        })
    }

    private fun delByType() {
        realmManager.deleteByType("中型轿车")
        queryAll()
    }

    private fun delByBrand() {
        realmManager.deleteByBrand("奔驰")
        queryAll()
    }

    private fun insertMultiData() {
        realmManager.insert(DataManager.cars)
        queryAll()

    }

    private fun deleteAll() {
        realmManager.deleteAllData()
        queryAll()
    }

    private fun updateData() {
        realmManager.updateData(Cars("丰田", "兰德酷路泽", "大型SUV", 7))
        realmManager.updateData(Cars("丰田", "皇冠", "中型轿车", 4))
        queryAll()
    }

    private fun queryAll() {
        realmManager.queryAll()?.addChangeListener(RealmChangeListener {
            adapter.setDatas(it)
        })
    }

    private fun queryByBrand() {
        realmManager.queryByBrand("丰田")?.addChangeListener(RealmChangeListener {
            adapter.setDatas(it)
        })
    }

    override fun onStop() {
        super.onStop()
        realmManager.cancelTransaction()
    }

    override fun onDestroy() {
        super.onDestroy()
        realmManager.close()
        vb?.apply {
            vb = null
        }

    }

}