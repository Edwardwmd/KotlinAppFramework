package com.edw.kotlinappframework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.edw.kotlinappframework.R


import com.edw.kotlinappframework.bean.GlideItem
import com.edw.kotlinappframework.ui.weight.GlideApp

class GlideShowAdapter(private val mC: Context, private val glideItems: MutableList<GlideItem>) :
    RecyclerView.Adapter<GlideShowAdapter.GlideShowViewHolder>() {
    inner class GlideShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGlide: ImageView = itemView.findViewById(R.id.iv_glide)
        val tvGlide: TextView = itemView.findViewById(R.id.tv_glide)
    }

    fun clearDatas() {
        glideItems.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GlideShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_glide, parent, false)
        return GlideShowViewHolder(view)
    }


    override fun onBindViewHolder(holder: GlideShowViewHolder, position: Int) {
        holder.tvGlide.text = glideItems[position].desc
        when (position) {
            0 -> GlideApp.with(mC)
                .load(glideItems[0].url)
                .centerCrop()
                .into(holder.ivGlide)
            1 -> GlideApp.with(mC)
                .load(glideItems[1].url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(holder.ivGlide)
            //placeholder布局占位符:占位符是当请求正在执行时被展示的 Drawable 。当请求成功完成时，占位符会被请求到的资源替换。
            2 -> GlideApp.with(mC)
                .load(glideItems[2].url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)//跳过缓存
                .placeholder(R.drawable.ic_glide_placeholder)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(holder.ivGlide)
            //error占位符:在请求永久性失败时展示。
            3 -> GlideApp.with(mC)
                .load(glideItems[3].url)
                .centerCrop()
                .error(R.drawable.ic_glide_error)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(holder.ivGlide)
            //fallback占位符:在请求的url/model为 null 时展示。设计 fallback Drawable 的主要目的是允许用户指示 null 是否为可接受的正常情况。
            4 -> GlideApp.with(mC)
                .load(glideItems[4].url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .fallback(R.drawable.ic_glide_fallback)
                .into(holder.ivGlide)
            5 -> Glide
                .with(mC)
                .load(glideItems[5].url)
                .thumbnail(Glide.with(mC).load(R.drawable.ic_glide_fallback).override(3))
                .into(holder.ivGlide)

        }
    }

    override fun getItemCount(): Int {
        return if (glideItems.isEmpty()) 0 else glideItems.size
    }

}
