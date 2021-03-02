package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.adapter.HiltDaggerTestAdapter
import com.edw.kotlinappframework.api.ProvinceApiService
import com.edw.kotlinappframework.bean.ToYoTa
import com.edw.kotlinappframework.databinding.ActivityHiltDaggerLearnBinding
import com.edw.kotlinappframework.di.hilt.qualifier.ActivityScope
import com.edw.kotlinappframework.utils.ConstantUtil.HILT_DAGGER_ACTIVITY_URI
import com.edw.kotlinappframework.utils.DataManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@Route(path = HILT_DAGGER_ACTIVITY_URI)
@AndroidEntryPoint
class HiltDaggerLearnActivity : AppCompatActivity() {

    private var vb: ActivityHiltDaggerLearnBinding? = null

    @ActivityScope
    @Inject
    lateinit var adapter: HiltDaggerTestAdapter


    @Inject
    lateinit var car: ToYoTa

    @Inject
    lateinit var provinceApiService: ProvinceApiService


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityHiltDaggerLearnBinding.inflate(this.layoutInflater)
        vb?.apply {
            setContentView(root)
            btnHiltAdapter.setOnClickListener {
                recyHilt.visibility = View.VISIBLE
                tvShowHiltContent.visibility = View.GONE
                adapter.setData(DataManager.hiltDatas)
                recyHilt.layoutManager = LinearLayoutManager(this@HiltDaggerLearnActivity)
                recyHilt.setHasFixedSize(true)
                recyHilt.adapter = adapter
            }

            btnHiltApplication.setOnClickListener {
                recyHilt.visibility = View.GONE
                tvShowHiltContent.visibility = View.VISIBLE
                tvShowHiltContent.text =
                    "这辆车${car.carCharacteristics()},${car.speedPerHour()},${car.drivenDistance()}"
            }

            btnHiltRxRetrofit.setOnClickListener {
                recyHilt.visibility = View.GONE
                tvShowHiltContent.visibility = View.VISIBLE
                provinceApiService.getProvinces()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { it ->
                        val sb = StringBuilder()
                        it?.onEach {
                            sb.append(" ").append(it.name)
                        }
                        tvShowHiltContent . text = sb . toString ()
                    }.doOnError {
                        it.message?.let { it1 -> Log.e("Network_Error:", it1) }
                    }
                    .subscribe {
                        Toast.makeText(this@HiltDaggerLearnActivity, "请求成功", Toast.LENGTH_SHORT)
                            .show()
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vb!!.recyHilt.visibility = View.GONE
        vb!!.tvShowHiltContent.visibility = View.GONE
        adapter.cleanData()
        if (vb != null) vb = null
    }
}