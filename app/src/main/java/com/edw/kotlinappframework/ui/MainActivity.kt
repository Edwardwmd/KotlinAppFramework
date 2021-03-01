package com.edw.kotlinappframework.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.edw.kotlinappframework.adapter.MainAdapter
import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.databinding.ActivityMainBinding
import com.edw.kotlinappframework.utils.AppUtils
import com.edw.kotlinappframework.utils.ConstantUtil
import com.edw.kotlinappframework.utils.DataManager
import com.google.android.material.snackbar.Snackbar

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ConstantUtil.MAIN_ACTIVITY_URI)
class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private var vb: ActivityMainBinding? = null
    private var adapter: MainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        adapter = MainAdapter(DataManager.buttonDatas)
        vb?.apply {
            setContentView(root)
            recy.setHasFixedSize(true)
            recy.layoutManager = LinearLayoutManager(this@MainActivity)
            recy.adapter = adapter
        }
        adapter?.setOnItemClickListener(this)

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.apply {
            val data =
                extras!!.getParcelable<ARouterTestBean>(ConstantUtil.MAIN_DATA_KEY) as ARouterTestBean
            with(data) {
                Snackbar.make(vb!!.root, "我是$pName,现在$pAge,我是$pDesc", 1200).show()
            }
        }
    }


    override fun onItemClick(itemView: View, position: Int) {

        when (position) {
            0 -> AppUtils.turnToActivity(this, GlideShowActivity().javaClass)
            1 -> AppUtils.turnToActivity(this, RetrofitLearnActivity().javaClass)
            2 -> AppUtils.turnToActivity(this, RxjavaLearnActivity().javaClass)
            3 -> AppUtils.turnToActivity(this, ArouterLearnActivity().javaClass)
            4 -> AppUtils.turnToActivity(this, EventBusLearnActivity().javaClass)
            5 -> ARouter.getInstance().build(ConstantUtil.KOIN_ACTIVITY_URI).navigation()
           6 -> ARouter.getInstance().build(ConstantUtil.HILT_DAGGER_ACTIVITY_URI).navigation()


        }

    }


    override fun onDestroy() {
        super.onDestroy()
        if (vb != null)
            vb = null
        if (adapter != null)
            adapter!!.cleanData()
        adapter = null
    }

}