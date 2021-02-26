package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.bean.EventMessageTest
import com.edw.kotlinappframework.databinding.ActivityTestBinding

import com.edw.kotlinappframework.utils.ConstantUtil
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Route(path = ConstantUtil.TEST_ACTIVITY_URI)
class TestActivity : AppCompatActivity() {
    //使用注解的形式获取ARouter发送过来的值  @Autowired注解需要明确发送过来的Key名
    @Autowired(name = ConstantUtil.TEST_DATA_KEY_1)
    @JvmField //@JvmField:消除了变量的getter与setter方法,修饰的变量不能是private属性的在kotlin中需要使用此注解
    var arouterBean: ARouterTestBean? = null

    @Autowired(name = ConstantUtil.TEST_POSITION)
    @JvmField
    var position: Int? = 0

    private var vb: ActivityTestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityTestBinding.inflate(layoutInflater)
        ARouter.getInstance().inject(this@TestActivity)
        setContentView(vb!!.root)
        showTextInTest()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    private fun showTextInTest() {
        when (position) {
            1000 -> showText()
            1001 -> showText()

        }
    }

    @SuppressLint("SetTextI18n")
    fun showText() {
        vb?.apply {
            with(arouterBean!!) {
               tvTest.text = "我是$pName,现在$pAge,我是$pDesc"
            }
            Log.e("AROUTER_Log", "已经输出结果")
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)

    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    internal fun getEventBusData(event: EventMessageTest?) {
        if (arouterBean == null) {
            event?.let {
                val aTb: ARouterTestBean = it.getMessage()!!
                with(aTb) {
                    vb!!.tvTest.text = "我是$pName,现在$pAge,我是$pDesc"
                }
                Log.e("EventBus_Log", "已经输出结果")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null)
            vb!!.tvTest.text = null
        vb = null
        if (arouterBean != null)
            arouterBean = null
    }
}