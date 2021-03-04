package com.edw.kotlinappframework.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edw.kotlinappframework.R
import javax.inject.Inject


/**
 * Author: EdwardWMD
 * Data: 2021/3/1
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */

class HiltDaggerTestAdapter @Inject constructor() :
    RecyclerView.Adapter<HiltDaggerTestAdapter.HiltDaggerViewHolder>() {
    private var hiltDaggerItems: MutableList<HiltItem> = ArrayList()

    class HiltDaggerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tv_hilt_adapter_show_item)
    }

    fun setData(hiltDaggerItems: MutableList<HiltItem>) {
        if (this.hiltDaggerItems.size <= 0) {
        this.hiltDaggerItems = hiltDaggerItems
        }
    }

    fun cleanData() {
        if (hiltDaggerItems.size > 0) {
            hiltDaggerItems.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HiltDaggerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hilt_item_view, parent, false)
        return HiltDaggerViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: HiltDaggerViewHolder,
        position: Int
    ) {
        hiltDaggerItems[position].apply {
            holder.tvText.text = "今天学了$subject,学习到了第 $page 页,学习的内容是:$content."
        }
    }

    override fun getItemCount(): Int {
        return hiltDaggerItems.run { if (isEmpty()) 0 else size }
    }
}