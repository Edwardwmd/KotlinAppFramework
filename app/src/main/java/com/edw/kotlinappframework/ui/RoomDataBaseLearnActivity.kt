package com.edw.kotlinappframework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.utils.ConstantUtil.ROOM_DATABASE_ACTIVITY_URI

@Route(path = ROOM_DATABASE_ACTIVITY_URI)
class RoomDataBaseLearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_data_base_learn)
    }
}