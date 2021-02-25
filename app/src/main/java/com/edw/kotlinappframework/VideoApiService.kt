package com.edw.kotlinappframework

import com.edw.kotlinappframework.bean.TodayVideoBean
import com.edw.kotlinappframework.utils.Api
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.POST

/**
 * Author: EdwardWMD
 * Data: 2021/2/18
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: 接口声明类
 */
interface VideoApiService {
    //单独使用Retrofit请求的回调
    @POST(Api.TODAYVIDEO_URL)
    fun showTodayVideoData(): Call<TodayVideoBean>

    //使用Rxjava和Retrofit相结合的回调
    @POST(Api.TODAYVIDEO_URL)
    fun showDataWithRxRetrofit():Observable<TodayVideoBean>

}