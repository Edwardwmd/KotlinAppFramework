package com.edw.kotlinappframework.bean

/**
 * Author: EdwardWMD
 * Data: 2021/2/27
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class KoinStudyBeanA(private val content: String) {
    fun getText(): String {
        return "注入框架成功,获取内容:$content"
    }

}
class KoinStudyBeanB(private val content: String,private val numberOfCourses:Int) {
    fun getText(): String {
        return "注入框架成功,获取内容:$content,现在是第${numberOfCourses}节课!!!"
    }

}

class KoinStudyBeanC(private val num1:Int, private val num2:Int) {
    fun getSum(): Int {
        return num1+num2
    }

}
