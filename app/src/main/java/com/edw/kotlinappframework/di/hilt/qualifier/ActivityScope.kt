package com.edw.kotlinappframework.di.hilt.qualifier

import javax.inject.Qualifier

/**
 * Author: EdwardWMD
 * Data: 2021/3/1
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * 定义依赖所作用的范围在Activity中
 */
@Qualifier//@Qualifier:用来解决依赖迷失,是一个限定符,使用注解来确定使用哪种 provide 方法
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ActivityScope {
}