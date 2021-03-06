package com.edw.kotlinappframework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edw.kotlinappframework.adapter.GlideShowAdapter
import com.edw.kotlinappframework.databinding.ActivityGlideShowBinding


import com.edw.kotlinappframework.utils.DataManager

class GlideShowActivity : AppCompatActivity() {

    private var vb: ActivityGlideShowBinding? = null
    private var adapter: GlideShowAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityGlideShowBinding.inflate(layoutInflater)
        adapter = GlideShowAdapter(this, DataManager.glideItems)
        vb?.let {
            setContentView(it.root)
            it.recyGlide.setHasFixedSize(true)
            it.recyGlide.layoutManager = LinearLayoutManager(this)
            it.recyGlide.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null) vb = null
        if (adapter != null)
            adapter!!.clearDatas()
        adapter = null
    }
}
