package com.edw.kotlinappframework

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.rxjava3.plugins.RxJavaPlugins

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
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
//        初始化ARouter
        ARouter.init(this)
        RxJavaPlugins.getErrorHandler()

    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}