package com.edw.kotlinappframework

import com.edw.kotlinappframework.api.Api
import com.edw.kotlinappframework.api.ApiDns
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

    private fun initClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .dns(ApiDns.genInstance("47.75.70.188",Api.PROVINCE_HOST))
            .addInterceptor(initInterceptor())
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private fun initInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun <T>create(apiService: Class<T>):T{
        return retrofit!!.create(apiService)
    }
}