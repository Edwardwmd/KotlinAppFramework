package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.edw.kotlinappframework.bean.TodayVideoBean
import com.edw.kotlinappframework.VideoApiService
import com.edw.kotlinappframework.databinding.ActivityRetrofitLearnBinding

import com.edw.kotlinappframework.utils.Api
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitLearnActivity : AppCompatActivity() {
    private var vb: ActivityRetrofitLearnBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityRetrofitLearnBinding.inflate(layoutInflater)
        setContentView(vb!!.root)
        initData()
    }

    fun initData() {
        //1.日志拦截器
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        //2.创建Okhttp对象
        val okhttp = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)//添加拦截器
            .build()
        //3.创建Retrofit对象
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Api.BASE_URL)//添加主机地址
            .client(okhttp)//添加Okhttp框架
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //4.创建请求接口类
        retrofit
            .create(VideoApiService::class.java)
            .showTodayVideoData()
            .enqueue(object : Callback<TodayVideoBean> {
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<TodayVideoBean>, response: Response<TodayVideoBean>
                ) {

                    val body = response.body()
                    body!!.result.forEach {
                        //在模拟器上打印结果
                        vb!!.tvRetrofitShow.text = it.data.toString()
                    }
                    Snackbar.make(vb!!.root, "数据请求成功!!", 2000).show()
                }

                override fun onFailure(call: Call<TodayVideoBean>, t: Throwable) {
                    t.message?.let { Log.e("Failure Response-->", it) }
                }
            })


    }
}