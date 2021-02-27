package com.edw.kotlinappframework

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

import com.edw.kotlinappframework.bean.KoinStudyBeanA
import com.edw.kotlinappframework.bean.KoinStudyBeanB
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Logger
import org.koin.dsl.module

/**
 * Author: EdwardWMD
 * Data: 2021/2/15
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class App : Application() {
    private val isDebug: Boolean = true

    override fun onCreate() {
        super.onCreate()
        //1.初始化ARouter
        initARouter()

        //初始化Koin依赖注入框架
        initKoin()


    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(KoinModuleManager.instance.appMode)
//            logger()
        }

    }

    fun initARouter() {
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        //初始化ARouter
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        //销毁ARouter
        ARouter.getInstance().destroy()
    }
}