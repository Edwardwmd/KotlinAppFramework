package com.edw.kotlinappframework.bean

/**
 * Author: EdwardWMD
 * Data: 2021/2/25
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class EventMessageTest(message: ARouterTestBean) {
    private var message: ARouterTestBean? = null
    init {
        this.message = message
    }
    internal fun getMessage(): ARouterTestBean? {
        return message
    }
    fun setMessage(message: ARouterTestBean) {
        this.message = message
    }

}