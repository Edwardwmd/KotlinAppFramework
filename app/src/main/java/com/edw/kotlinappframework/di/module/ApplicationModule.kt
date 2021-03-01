package com.edw.kotlinappframework.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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


}