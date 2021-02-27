package com.edw.kotlinappframework.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route

import com.edw.kotlinappframework.bean.KoinStudyBeanA
import com.edw.kotlinappframework.bean.KoinStudyBeanB
import com.edw.kotlinappframework.bean.KoinStudyBeanC
import com.edw.kotlinappframework.databinding.ActivityKoinLearnBinding
import com.edw.kotlinappframework.utils.ConstantUtil
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope
import kotlin.reflect.KProperty

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

    //Koin单个参数依赖注入(single)
    val koinStudyBeanA: KoinStudyBeanA by inject { parametersOf("学习Koin!!") }

    //Koin多个参数依赖注入(single)
    val koinStudyBeanB: KoinStudyBeanB by inject { parametersOf("学习Koin依赖注入", 5) }

    //通过scope.inject多个参数依赖注入(scope)
    val koinScropC: KoinStudyBeanC by scope.inject { parametersOf(num1, num2) }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityKoinLearnBinding.inflate(layoutInflater)
        vb?.apply {
            setContentView(root)
            btnSampleKoin.setOnClickListener {
                tvKoinContent.visibility = View.VISIBLE
                tvKoinContent.text = koinStudyBeanA.getText()
            }
            btnMultiParameterKoin.setOnClickListener {
                tvKoinContent.visibility = View.VISIBLE
                tvKoinContent.text = koinStudyBeanB.getText()
            }
            btnMultiScopeKoin.setOnClickListener {
                tvKoinContent.visibility = View.VISIBLE
                tvKoinContent.text = "$num1+$num2=${koinScropC.getSum()}"
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


