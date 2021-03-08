package com.edw.kotlinappframework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.databinding.ActivityCoilLoaderBinding
import com.edw.kotlinappframework.utils.ConstantUtil
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope


@Route(path = ConstantUtil.COIL_LOADER_ACTIVITY_URI)
class CoilLoaderActivity : AppCompatActivity(), KoinScopeComponent {
    override val scope: Scope by lazy { newScope(this) }

    private var vb: ActivityCoilLoaderBinding? = null

    private val imageLoader: ImageLoader by scope.inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityCoilLoaderBinding.inflate(layoutInflater)
        vb?.apply {
            setContentView(root)
            //1.普通模式
            btnNormalWayShow.setOnClickListener {
                ivCoilShow.load(ConstantUtil.PIC_URL4) {
                    crossfade(true)//淡入淡出
                    placeholder(R.drawable.ic_glide_placeholder)//加载时占位符
                    error(R.drawable.ic_glide_error)//发生错误时占位符
                }
            }
            //2.加载Gif,添加imageLoader图片解码器
            btnGifShow.setOnClickListener {
                ivCoilShow.load(ConstantUtil.PIC_URL5, imageLoader) {
                    crossfade(true)//淡入淡出
                    placeholder(R.drawable.ic_glide_placeholder)//加载时占位符
                    error(R.drawable.ic_glide_error)//发生错误时占位符
                }
            }

            //3.加载SVG
            btnSvgShow.setOnClickListener {
                ivCoilShow.load(ConstantUtil.PIC_URL6, imageLoader) {
                    crossfade(true)//淡入淡出
                    placeholder(R.drawable.ic_glide_placeholder)//加载时占位符
                    error(R.drawable.ic_glide_error)//发生错误时占位符
                }
            }

            btnTransitionShow.setOnClickListener {

            }

        }
    }
}