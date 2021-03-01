package com.edw.kotlinappframework.api.imp

import com.edw.kotlinappframework.api.Api
import com.edw.kotlinappframework.api.KoinStudyService

/**
 * Author: EdwardWMD
 * Data: 2021/2/28
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * 模仿网络请求到结果和获取错误
 */
class KoinStudyServiceImp: KoinStudyService {
    override fun getResponseContent(): String {
       return "${Api.BASE_URL}${Api.TODAYVIDEO_URL}"
    }

    override fun getErrorText(): String {
       return "网络连接错误"
    }
}