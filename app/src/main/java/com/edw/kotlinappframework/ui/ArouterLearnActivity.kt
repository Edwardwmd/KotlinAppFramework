package com.edw.kotlinappframework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.databinding.ActivityArouterLearnBinding


import com.edw.kotlinappframework.utils.ConstantUtil

@Route(path = ConstantUtil.AROUTER_ACTIVITY_URI)
class ArouterLearnActivity : AppCompatActivity() {

    private var vb: ActivityArouterLearnBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityArouterLearnBinding.inflate(layoutInflater)
        vb?.apply {
            setContentView(root)
            btnArouterTurn.setOnClickListener {
                //携带参数跳转(获取参数页面使用代码动态获取)
                ARouter
                    .getInstance()
                    .build(ConstantUtil.MAIN_ACTIVITY_URI)
                    .withParcelable(
                        ConstantUtil.MAIN_DATA_KEY,
                        ARouterTestBean("张三丰", 149, "太极拳创始人!!!")
                    )//传值
                    .navigation()
            }

            btnArouterUrlTurn.setOnClickListener {
                //携带参数跳转(使用注解的方式获取传过去的值)
                ARouter
                    .getInstance()
                    .build(ConstantUtil.TEST_ACTIVITY_URI)
                    .withInt(ConstantUtil.TEST_POSITION, 1000)
                    .withParcelable(
                        ConstantUtil.TEST_DATA_KEY_1,
                        ARouterTestBean("杨过", 38, "黯然销魂掌开山鼻祖^_^")
                    )
                    .navigation()

            }
            btnAnimatedParameterJump.setOnClickListener {
                ARouter.getInstance()
                    .build(ConstantUtil.TEST_ACTIVITY_URI)
                    .withInt(ConstantUtil.TEST_POSITION, 1001)
                    .withParcelable(
                        ConstantUtil.TEST_DATA_KEY_1,
                        ARouterTestBean("黄飞鸿", 28, "佛山无影脚开创者^_^")
                    )
                    .withTransition(R.anim.fade_in, R.anim.fade_out)
                    .navigation(this@ArouterLearnActivity)

            }
        }
    }
}