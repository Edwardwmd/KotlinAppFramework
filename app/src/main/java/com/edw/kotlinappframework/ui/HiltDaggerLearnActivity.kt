package com.edw.kotlinappframework.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.adapter.HiltDaggerTestAdapter
import com.edw.kotlinappframework.bean.ToYoTa
import com.edw.kotlinappframework.databinding.ActivityHiltDaggerLearnBinding
import com.edw.kotlinappframework.di.qualifier.ActivityScope
import com.edw.kotlinappframework.utils.ConstantUtil.HILT_DAGGER_ACTIVITY_URI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Route(path = HILT_DAGGER_ACTIVITY_URI)
@AndroidEntryPoint
class HiltDaggerLearnActivity : AppCompatActivity() {

    private var vb: ActivityHiltDaggerLearnBinding? = null

    @ActivityScope
    @Inject
    lateinit var adapter: HiltDaggerTestAdapter

//    @Singleton
    @Inject
    lateinit var car: ToYoTa


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityHiltDaggerLearnBinding.inflate(this.layoutInflater)
        vb?.apply {
            setContentView(root)
            btnHiltAdapter.setOnClickListener {
                Toast.makeText(
                    this@HiltDaggerLearnActivity,
                    "这辆车${car.carCharacteristics()},${car.speedPerHour()},${car.drivenDistance()}",
                    Toast.LENGTH_SHORT
                ).show()
                recyHilt.visibility = View.VISIBLE
                recyHilt.layoutManager = LinearLayoutManager(this@HiltDaggerLearnActivity)
                recyHilt.setHasFixedSize(true)
                recyHilt.adapter = adapter
            }

        }
    }
}