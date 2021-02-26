package com.edw.kotlinappframework.utils

import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import java.lang.NullPointerException

/**
 * Author: EdwardWMD
 * Data: 2021/2/22
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: 按钮防抖动点击事件
 */
object RxUtils {
    private fun <T> checkNull(value: T?) {
        if (value == null)
            throw NullPointerException("RxUtils->ViewClickOnSubscribe is Null")
    }

    /**
     * 按钮防抖动点击事件
     */
    fun setViewClickOnSubscribe(v: View): Observable<View> {
        checkNull(v)
        return Observable.create(ViewClickOnSubscribe(v))
    }

    private class ViewClickOnSubscribe(private val v: View) : ObservableOnSubscribe<View> {
        override fun subscribe(emitter: ObservableEmitter<View>?) {
            checkNull(v)
            v.setOnClickListener {
                if (!emitter!!.isDisposed) emitter.onNext(it)
            }

        }

    }
}