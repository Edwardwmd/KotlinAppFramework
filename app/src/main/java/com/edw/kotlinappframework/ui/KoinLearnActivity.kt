package com.edw.kotlinappframework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.utils.ConstantUtil

@Route(path = ConstantUtil.KOIN_ACTIVITY_URI)
class KoinLearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin_learn)
    }
}