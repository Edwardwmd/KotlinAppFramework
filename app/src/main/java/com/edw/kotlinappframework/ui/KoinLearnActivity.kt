package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.net.RetrofitClient
import com.edw.kotlinappframework.api.ProvinceApiService
import com.edw.kotlinappframework.api.imp.KoinStudyServiceImp
import com.edw.kotlinappframework.bean.KoinStudyBeanA
import com.edw.kotlinappframework.bean.KoinStudyBeanB
import com.edw.kotlinappframework.bean.KoinStudyBeanC
import com.edw.kotlinappframework.databinding.ActivityKoinLearnBinding

import com.edw.kotlinappframework.utils.ConstantUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable.intervalRange
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope
import java.util.concurrent.TimeUnit

@Route(path = ConstantUtil.KOIN_ACTIVITY_URI)
class KoinLearnActivity : AppCompatActivity(), KoinScopeComponent {
    //使用Scope需要实现KoinScopeComponent接口
//初始化Scope(重写变量),有两种方法实现:
//第一种:通过by lazy的方法初始化newScope(推荐使用):
    override val scope: Scope by lazy { newScope(this) }
//第二种:通过get()初始化newScope:
//override val scope: Scope
//    get() = newScope(this)

    private var vb: ActivityKoinLearnBinding? = null
    private val num1 = 84
    private val num2 = 1865
    private val count = 5L

    //Koin单个参数依赖注入(single)
    val koinStudyBeanA: KoinStudyBeanA by inject { parametersOf("学习Koin!!") }

    //Koin多个参数依赖注入(single)
    val koinStudyBeanB: KoinStudyBeanB by inject { parametersOf("学习Koin依赖注入", 5) }

    //通过scope.inject多个参数依赖注入(scope)
    val koinScropC: KoinStudyBeanC by scope.inject { parametersOf(num1, num2) }

    //通过single bind实现依赖注入
    private val bindSingleApi: KoinStudyServiceImp by inject()

    private val client: RetrofitClient by inject()

//    private val apiService:ProvinceApiService by inject { parametersOf(retrofit)  }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityKoinLearnBinding.inflate(layoutInflater)
        vb?.apply {
            setContentView(root)
            btnSampleKoin.setOnClickListener {
                if (!tvKoinContent.isVisible)
                    tvKoinContent.visibility = View.VISIBLE
                tvKoinContent.text = koinStudyBeanA.getText()
            }
            btnMultiParameterKoin.setOnClickListener {
                if (!tvKoinContent.isVisible)
                    tvKoinContent.visibility = View.VISIBLE
                tvKoinContent.text = koinStudyBeanB.getText()
            }
            btnMultiScopeKoin.setOnClickListener {
                if (!tvKoinContent.isVisible)
                    tvKoinContent.visibility = View.VISIBLE
                tvKoinContent.text = "$num1+$num2=${koinScropC.getSum()}"
            }
            btnSingleBindKoin.setOnClickListener {
                if (!tvKoinContent.isVisible)
                    tvKoinContent.visibility = View.VISIBLE
                intervalRange(0, count, 0, 1, TimeUnit.SECONDS)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        tvKoinContent.text = """获取Api:${bindSingleApi.getResponseContent()} 
                            还剩余:${count - 1 - it} 秒显示下一段内容!!!
                        """.trimMargin()

                    }
                    .doOnError {
                        tvKoinContent.text = "错误:${bindSingleApi.getErrorText()}"
                    }.doOnComplete {
                        tvKoinContent.text = "显示完成!!!"
                    }
                    .subscribe()
            }

            btnRetrofitKoin.setOnClickListener {
                if (!tvKoinContent.isVisible)
                    tvKoinContent.visibility = View.VISIBLE
                client
                    .create(ProvinceApiService::class.java)
                    .getProvinces()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { it1 ->
                        val sb = StringBuilder()
                        it1.forEach {
                            sb.append(" ").append(it.name)
                        }
                        tvKoinContent.text = sb.toString()
                    }.doOnError {
                        Toast.makeText(this@KoinLearnActivity, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }.doOnComplete {
                        Toast.makeText(this@KoinLearnActivity, "请求完成^_^", Toast.LENGTH_SHORT).show()
                    }.subscribe()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        vb!!.tvKoinContent.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null)
            vb = null
        if (!scope.closed)
            scope.close()


    }


}


