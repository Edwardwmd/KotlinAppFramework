package com.edw.kotlinappframework.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.edw.kotlinappframework.databinding.ActivityRoomDataBaseLearnBinding
import com.edw.kotlinappframework.db.room.StudentDAO
import com.edw.kotlinappframework.utils.AppUtils.isMainThread
import com.edw.kotlinappframework.utils.ConstantUtil.ROOM_DATABASE_ACTIVITY_URI
import com.edw.kotlinappframework.utils.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

@Route(path = ROOM_DATABASE_ACTIVITY_URI)
class RoomDataBaseLearnActivity : AppCompatActivity() {

    private val studentDao: StudentDAO by inject()

    private var job: Job? = null

    private var vb: ActivityRoomDataBaseLearnBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityRoomDataBaseLearnBinding.inflate(layoutInflater)
        vb?.apply {
            setContentView(root)
            btnInsertAll.setOnClickListener {
                insertMultiData()
            }
            btnUpdateData.setOnClickListener {
                updateData()
            }

            btnDeleteAll.setOnClickListener {
                deleteAllData()
            }
            btnDeleteByid.setOnClickListener {
                delDataById()
            }

            btnDeleteByname.setOnClickListener {
                delDataByName()
            }
            btnDeleteBysex.setOnClickListener {
                delDataBySex()
            }
            refreshData()
        }
    }

    private fun delDataBySex() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            studentDao.deleteBySex("男")
            loadThreadLog("根据性别删除")
        }

    }

    private fun delDataByName() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            studentDao.deleteByName("张三丰")
            loadThreadLog("根据姓名删除")
        }
    }

    private fun delDataById() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            studentDao.deleteById(1)
            studentDao.deleteById(2)
            loadThreadLog("根据ID删除")
        }

    }

    private fun refreshData() {
        studentDao.queryAll().observe(this@RoomDataBaseLearnActivity, {
            job = lifecycleScope.launch(Dispatchers.IO) {//子线程IO处理数据库耗时操作
                loadThreadLog("查询刷新数据---->外")
                val sb = StringBuilder()
                it.forEach {
                    sb.append(it.id).append(" ").append(it.name).append(" ").append(it.sex).append(" ").append(it.skill)
                        .append("\n")
                }
                withContext(Dispatchers.Main) {//切换主线程显示UI
                    vb!!.tvRoom.text = sb.toString()
                    loadThreadLog("查询刷新数据---->内")
                }
            }
        })
    }

    private fun deleteAllData() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            studentDao.deleteAll()
            loadThreadLog("删除数据")
        }
    }

    private fun updateData() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            studentDao.updateData(2, "武松", "男","力大无穷")
            studentDao.updateData(8, "扈三娘", "女","彪悍")
            studentDao.updateData(11, "姜子牙", "男","周易卜卦")
            studentDao.updateData(13, "妲己", "女","迷惑")
            loadThreadLog("修改数据")
        }
    }

    private fun insertMultiData() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            studentDao.insertAll(DataManager.studentData)
            loadThreadLog("插入数据")
        }
    }

    private fun loadThreadLog(method: String) {
        Log.e(
            "协程线程---->",
            "协程执行$this ----> $method   线程id：${Thread.currentThread().id} 当前是否为主线程:${isMainThread()}"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null)
            vb = null
        job?.cancel()
        job = null
    }


}