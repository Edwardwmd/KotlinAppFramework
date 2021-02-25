package com.edw.kotlinappframework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edw.kotlinappframework.adapter.GlideShowAdapter
import com.edw.kotlinappframework.databinding.ActivityGlideShowBinding


import com.edw.kotlinappframework.utils.DataManager

class GlideShowActivity : AppCompatActivity() {
    private var vb: ActivityGlideShowBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityGlideShowBinding.inflate(layoutInflater)
        setContentView(vb!!.root)
        val adapter = GlideShowAdapter(this, DataManager.glideItems)
        vb!!.recyGlide.setHasFixedSize(true)
        vb!!.recyGlide.layoutManager = LinearLayoutManager(this)
        vb!!.recyGlide.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null) vb = null
    }
}
