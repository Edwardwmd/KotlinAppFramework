package com.edw.kotlinappframework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.databinding.ActivityArouterLearnBinding


import com.edw.kotlinappframework.utils.ConstantUtil

@Route(path = ConstantUtil.AROUTER_ACTIVITY_URI)
class ArouterLearnActivity : AppCompatActivity() {

    private var vb: ActivityArouterLearnBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityArouterLearnBinding.inflate(layoutInflater)
        setContentView(vb!!.root)
        vb!!.btnArouterTurn.setOnClickListener {
            //携带参数跳转(获取参数页面使用代码动态获取)
            ARouter
                .getInstance()
                .build(ConstantUtil.MAIN_ACTIVITY_URI)
                .withParcelable("GLE", ARouterTestBean("张三丰", 149, "太极创始人"))//传值
                .navigation()
        }

        vb!!.btnArouterUrlTurn.setOnClickListener {
            //携带参数跳转(使用注解的方式获取传过去的值)
            ARouter
                .getInstance()
                .build(ConstantUtil.TEST_ACTIVITY_URI)
                .withParcelable("GLE", ARouterTestBean("杨过", 38, "黯然销魂掌"))
                .navigation()
        }
    }
}