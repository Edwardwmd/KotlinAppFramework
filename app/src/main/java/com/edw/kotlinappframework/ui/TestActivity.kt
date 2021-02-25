package com.edw.kotlinappframework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.edw.kotlinappframework.R

import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.utils.ConstantUtil

@Route(path = ConstantUtil.TEST_ACTIVITY_URI)
class TestActivity : AppCompatActivity() {
    @Autowired(name = "GLE")
    @JvmField
    var arouterBean: ARouterTestBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        ARouter.getInstance().inject(this@TestActivity)
        Toast.makeText(
            this,
            "我是${arouterBean?.pName},现在${arouterBean?.pAge},我是${arouterBean?.pDesc}",
            Toast.LENGTH_SHORT
        ).show()
    }

}