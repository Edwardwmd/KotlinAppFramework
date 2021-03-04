package com.edw.kotlinappframework.di.hilt.module

import com.edw.kotlinappframework.api.Api
import com.edw.kotlinappframework.api.ProvinceApiService
import com.edw.kotlinappframework.bean.ToYoTa
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Author: EdwardWMD
 * Data: 2021/3/1
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
@Module
@InstallIn(SingletonComponent::class)//Install 用来告诉 Hilt 这个模块会被安装到哪个组件上.
object ApplicationModule {


    @Singleton
    @Provides
    fun provideCar(): ToYoTa {
        return ToYoTa(256.23, 45623.875, "汉兰达,是帝王的尊享!")
    }

    @Provides
    fun provideBaseUrl() = Api.PROVINCE_BASE_URL

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun privideOkhttp(interceptor:Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit):ProvinceApiService{
        return retrofit.create(ProvinceApiService::class.java)
    }


}