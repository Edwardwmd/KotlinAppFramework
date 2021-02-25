package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.adapter.MainAdapter
import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.bean.EventMessageTest
import com.edw.kotlinappframework.databinding.ActivityMainBinding


import com.edw.kotlinappframework.utils.AppUtils
import com.edw.kotlinappframework.utils.ConstantUtil
import com.edw.kotlinappframework.utils.DataManager
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ConstantUtil.MAIN_ACTIVITY_URI)
class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private var vb: ActivityMainBinding? = null
    private var adapter: MainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb!!.root)
        adapter = MainAdapter(DataManager.buttonDatas)
        vb!!.recy.setHasFixedSize(true)
        vb!!.recy.layoutManager = LinearLayoutManager(this)
        vb!!.recy.adapter = adapter
        adapter!!.setOnItemClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent != null && intent.extras != null) {
            val data =
                intent.extras?.getParcelable<ARouterTestBean>(ConstantUtil.MAIN_DATA_KEY) as ARouterTestBean
            Snackbar.make(vb!!.root, "我是${data.pName},现在${data.pAge},我是${data.pDesc}", 1200).show()
        }


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

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    internal   fun getEventBusData(event: EventMessageTest) {
        val aTb: ARouterTestBean = event.getMessage()!!
        Toast.makeText(this, "我是${aTb.pName},现在${aTb.pAge},我是${aTb.pDesc}", Toast.LENGTH_SHORT)
            .show()
        Log.e("EventBus_Log", "已经输出结果")
    }


    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null)
            vb = null
        if (adapter != null)
            adapter!!.cleanData()
        adapter = null
    }

}