package com.edw.kotlinappframework.net

import com.edw.kotlinappframework.api.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: EdwardWMD
 * Data: 2021/3/2
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: 管理网络请求Retrofit+okhttp的类
 */
class RetrofitClient private constructor() {
    private var retrofit: Retrofit? = null

    companion object {
        val INSTANCE: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }

    init {
        retrofit = Retrofit
            .Builder()
            .baseUrl(Api.PROVINCE_BASE_URL)
            .client(initClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //使用Okhttp作为网络请求的内核
    private fun initClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .dns(ApiDns.genInstance("47.75.70.188", Api.PROVINCE_HOST))
            .addInterceptor(initInterceptor())
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    //添加拦截器
    private fun initInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //创建接口的服务
    fun <T> create(apiService: Class<T>): T {
        return retrofit!!.create(apiService)
    }
}