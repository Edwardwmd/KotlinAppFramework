package com.edw.kotlinappframework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.bean.ARouterTestBean
import com.edw.kotlinappframework.bean.EventMessageTest
import com.edw.kotlinappframework.databinding.ActivityEventBusLearnBinding
import com.edw.kotlinappframework.utils.AppUtils
import com.edw.kotlinappframework.utils.ConstantUtil
import org.greenrobot.eventbus.EventBus

@Route(path = ConstantUtil.EVENTBUS_ACTIVITY_URI)
class EventBusLearnActivity : AppCompatActivity() {
    private var vb: ActivityEventBusLearnBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityEventBusLearnBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        vb!!.btnEventbusTurn.setOnClickListener {
            EventBus.getDefault().postSticky(
                EventMessageTest(ARouterTestBean(
                        "李小龙",
                        33,
                        "双截棍创始人!!!"
                    ))
            )
//                MessageEvent().put(
//                    ARouterTestBean(
//                        "李小龙",
//                        33,
//                        "双截棍创始人!!!"
//                    )
//                )
//            )
//            ARouter.getInstance().build(ConstantUtil.TEST_ACTIVITY_URI).navigation()

        }

    }
}