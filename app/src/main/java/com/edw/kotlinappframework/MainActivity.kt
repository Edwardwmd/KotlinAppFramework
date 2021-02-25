package com.edw.kotlinappframework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.ui.*
import com.edw.kotlinappframework.adapter.MainAdapter
import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.databinding.ActivityMainBinding


import com.edw.kotlinappframework.utils.AppUtils
import com.edw.kotlinappframework.utils.ConstantUtil
import com.edw.kotlinappframework.utils.DataManager
import com.google.android.material.snackbar.Snackbar

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ConstantUtil.MAIN_ACTIVITY_URI)
class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb!!.root)
        val adapter = MainAdapter(DataManager.buttonDatas)
        vb!!.recy.setHasFixedSize(true)
        vb!!.recy.layoutManager = LinearLayoutManager(this)
        vb!!.recy.adapter = adapter
        adapter.setOnItemClickListener(this)

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent != null && intent.extras != null) {
            val data = intent.extras?.getParcelable<ARouterTestBean>("GLE") as ARouterTestBean
            Snackbar.make(vb!!.root, "我是${data.pName},现在${data.pAge},我是${data.pDesc}", 1200).show()
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        if (vb != null)
            vb = null
    }

    override fun onItemClick(itemView: View, position: Int) {

        when (position) {
            0 -> AppUtils.turnToActivity(this, GlideShowActivity().javaClass)
            1 -> AppUtils.turnToActivity(this, RetrofitLearnActivity().javaClass)
            2 -> AppUtils.turnToActivity(this, RxjavaLearnActivity().javaClass)
            3 -> AppUtils.turnToActivity(this, ArouterLearnActivity().javaClass)
            4 -> AppUtils.turnToActivity(this, EventBusLearnActivity().javaClass)


        }

    }


}