package com.edw.kotlinappframework

import android.icu.text.Normalizer2
import com.edw.kotlinappframework.bean.KoinStudyBeanA
import com.edw.kotlinappframework.bean.KoinStudyBeanB
import com.edw.kotlinappframework.bean.KoinStudyBeanC
import com.edw.kotlinappframework.ui.KoinLearnActivity
import io.reactivex.rxjava3.schedulers.Schedulers.single
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

/**
 * Author: EdwardWMD
 * Data: 2021/2/27
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: 管理Koin依赖注入对象的类
 */
class KoinModuleManager private constructor() {
    //双重锁检测单例模式
    companion object {
        val instance: KoinModuleManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            KoinModuleManager()
        }
    }

    //添加需要Koin依赖注入的对象,详情文档在@link https://insert-koin.io/
    //1.single:定义，创建一个在整个容器生命周期内都持续存在的对象（不能删除）。
    //2.factory:定义，每次创建一个新对象。短暂的。容器中没有持久性（无法共享）。
    //3.scoped 定义，创建一个持久关联到关联作用域生存期的对象。
    val appMode = module {
        //单个参数(单例模式)
        single { (content: String) -> KoinStudyBeanA(content) }
        //多个参数(单例模式)
        single { (content: String, num: Int) ->
            KoinStudyBeanB(content, num)
        }
        //scope多个参数
        scope<KoinLearnActivity> {
            scoped { (n1: Int, n2: Int) ->
                KoinStudyBeanC(n1, n2)
            }
        }
    }

//    val bMode= module{
//        single { (n1:Int,n2:Int) -> KoinStudyBeanC(n1,n2) }
//    }

}