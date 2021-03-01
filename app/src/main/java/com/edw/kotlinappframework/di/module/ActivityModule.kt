package com.edw.kotlinappframework.di.module

import com.edw.kotlinappframework.adapter.HiltDaggerTestAdapter
import com.edw.kotlinappframework.di.qualifier.ActivityScope
import com.edw.kotlinappframework.utils.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Author: EdwardWMD
 * Data: 2021/3/1
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @ActivityScope
    @Provides
    fun provide(): HiltDaggerTestAdapter {
        return HiltDaggerTestAdapter(DataManager.hiltDatas)
    }

}