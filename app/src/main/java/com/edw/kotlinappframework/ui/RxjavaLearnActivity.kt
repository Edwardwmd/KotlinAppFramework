package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.bumptech.glide.request.RequestOptions

import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.api.VideoApiService
import com.edw.kotlinappframework.databinding.ActivityRxjavaLearnBinding


import com.edw.kotlinappframework.api.Api
import com.edw.kotlinappframework.api.ApiDns
import com.edw.kotlinappframework.ui.weight.GlideApp
import com.edw.kotlinappframework.utils.RxUtils
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.core.Observable.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RxjavaLearnActivity : AppCompatActivity() {

    private var vb: ActivityRxjavaLearnBinding? = null
    private var subscribe: Disposable? = null
    private var upstream: String? = null
    private var sum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityRxjavaLearnBinding.inflate(layoutInflater)
        setContentView(vb!!.root)
        //倒计时跳转
        vb!!.btnRxturn.setOnClickListener { rxCountdown() }
        //延时跳转
        vb!!.btnRxdelay.setOnClickListener { rxDelayToTurn() }
        //线程切换
        vb!!.btnThreadChange.setOnClickListener { rxThreadLearn() }
        //结合Rxjava的防抖动点击事件
        vb!!.btnAntiShake.setOnShakeProofClickListener {
            Snackbar.make(it, "防抖动点击:${sum++}", 200).show()
        }
        //与Retrofit结合的网络请求
        subscribe = RxUtils
            .setViewClickOnSubscribe(vb!!.btnRxretrofit)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { rxRetrofitNetwork() }
    }

    /**
     * 结合Rxjava和Retrofit框架实现网络请求功能
     */
    private fun rxRetrofitNetwork() {
        //初始化okhttp
        val okhttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(20, TimeUnit.SECONDS)//连接超时设置
            .readTimeout(20, TimeUnit.SECONDS)//读取超时
            .writeTimeout(20, TimeUnit.SECONDS)//写入超时
            .dns(ApiDns.INSTANCE)//添加自定义DNS
            .build()
        //初始化Retrofit
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Api.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        //网络请求数据
        subscribe = retrofit
            .create(VideoApiService::class.java)
            .showDataWithRxRetrofit()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Snackbar.make(vb!!.root, "拿到数据---->${it.result[1].data.header.icon}", 1000).show()

            }.doOnError {
                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            .subscribe {

                vb!!.ivRxretrofit.visibility = View.VISIBLE
                GlideApp
                    .with(this)
                    .load(it.result[1].data.header.icon)
                    .placeholder(R.drawable.ic_glide_placeholder)
                    .error(R.drawable.ic_glide_error)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(vb!!.ivRxretrofit)

            }


    }

    private fun rxDelayToTurn() {
        subscribe = timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                startActivity(Intent(this, MainActivity::class.java))
            }
    }

    /**
     * 线程切换
     * Schedulers详单与一个线程池,线程调度类型有五种:
     * AndroidSchedulers:是在Android上使用的特殊线程,里面有mainThread()主线程
     */
    @SuppressLint("SetTextI18n")
    private fun rxThreadLearn() {
        subscribe = create<Any?> {
            upstream = "上游线程为:${Thread.currentThread().id}  上游线程是否为主线程:${isMainThread()}"
            it.onNext("Student!!")
            it.onNext(2)
            it.onNext(55.264f)
            it.onComplete()
        }.subscribeOn(Schedulers.io())//线程切换(子线程),上游数据操作在子线程执行
            .observeOn(AndroidSchedulers.mainThread())//线程切换(主线程),下游数据获取数据展示在主线程执行
            .subscribe {
                vb!!.tvShowThread.visibility = View.VISIBLE
                vb!!.tvShowThread.text =
                    "${upstream} ; 下游的线程为:${Thread.currentThread().id},  下游线程是否为主线程:${isMainThread()}, 下游接受的数据:${it}"

            }


    }

    /**
     * 倒计时跳转
     * intervalRange参数含义,start:从这个值开始计数,count:终止的值(计数结束的值),initialDelay:初始的延时,period:延时间隔,unit:计数大的单位
     */
    @SuppressLint("SetTextI18n")
    private fun rxCountdown() {
        subscribe = intervalRange(0, 4, 0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())//子线程执行延时操作
            .observeOn(AndroidSchedulers.mainThread())//主线程执行数据展示
            .doOnNext { vb!!.btnRxturn.text = "倒计时:${3 - it}s" }
            .doOnComplete {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.subscribe()

    }

    /**
     * 判断线程是否在主线程上
     */
    private fun isMainThread(): String =
        if (Looper.getMainLooper().thread == Thread.currentThread()) "是" else "不是"

    override fun onDestroy() {
        super.onDestroy()
        (subscribe != null) and (!subscribe!!.isDisposed)
        subscribe!!.dispose()
        subscribe = null

        if (vb != null)
            vb = null


    }

    /**
     * 按钮防抖动点击事件,使用Kotlin的扩展函数实现
     */

    private inline fun View.setOnShakeProofClickListener(
        duration: Long = 1000,
        unit: TimeUnit = TimeUnit.MILLISECONDS,
        crossinline listener: (v: View) -> Unit
    ): Disposable {
        return create(ObservableOnSubscribe<View> { emiter ->
            setOnClickListener {
                if (!emiter.isDisposed) emiter.onNext(it)
            }
        }).throttleFirst(duration, unit).subscribe {
            listener(it)
        }
    }

    fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }
}