package com.edw.kotlinappframework.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edw.kotlinappframework.R
import com.edw.kotlinappframework.db.realm.Cars

/**
 * Author: EdwardWMD
 * Data: 2021/3/7
 * Project: RxRoom
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class RealmShowAdapter : RecyclerView.Adapter<RealmShowAdapter.RealmViewHolder>() {
    private var datas: MutableList<Cars> = ArrayList()

    inner class RealmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSeats: TextView = itemView.findViewById(R.id.tv_seats)
        val tvCarBrand: TextView = itemView.findViewById(R.id.tv_car_brand)
        val tvCarType: TextView = itemView.findViewById(R.id.tv_car_type)
    }

    fun setDatas(datas: MutableList<Cars>?) {
        this.datas = datas!!
        notifyDataSetChanged()
    }

    fun clearData() {
        if (datas.size > 0)
            this.datas.clear()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RealmShowAdapter.RealmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_realm, parent, false)
        return RealmViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RealmShowAdapter.RealmViewHolder, position: Int) {
        datas[position].apply {
            holder.tvSeats.text = "$numOfCarSeats 座"
            holder.tvCarBrand.text = "$carBrand - $carName"
            holder.tvCarType.text = carType
        }
    }

    override fun getItemCount(): Int {
        return if (datas.size <= 0) 0 else datas.size
    }


}