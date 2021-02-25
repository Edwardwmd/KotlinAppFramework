package com.edw.kotlinappframework.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.edw.kotlinappframework.R

import com.edw.kotlinappframework.bean.ButtonData


/**
 * Author: EdwardWMD
 * Data: 2021/2/15
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class MainAdapter(private val buttonDatas: MutableList<ButtonData>?) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvItem: CardView = itemView.findViewById(R.id.cv_item)
        val tvItemName: TextView = itemView.findViewById(R.id.tv_item_name)
    }

    fun cleanData() {
        buttonDatas!!.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        val mainViewHolder = MainViewHolder(view)
        mainViewHolder.itemView.setOnClickListener {
            onItemClickListener!!.onItemClick(it, mainViewHolder.adapterPosition)
        }
        return mainViewHolder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.cvItem.setCardBackgroundColor(Color.parseColor(buttonDatas!![position].color))
        holder.tvItemName.text = buttonDatas[position].name

    }

    override fun getItemCount(): Int {
        return if (buttonDatas!!.isEmpty()) 0 else buttonDatas.size
    }

    interface OnItemClickListener {
        fun onItemClick(itemView: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}

