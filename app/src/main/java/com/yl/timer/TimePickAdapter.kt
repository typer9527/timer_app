package com.yl.timer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TimePickAdapter : RecyclerView.Adapter<TimePickAdapter.TimeHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TimeHolder {
        val inflate = LayoutInflater.from(p0.context).inflate(R.layout.item_time, p0, false)
        return TimeHolder(inflate)
    }

    override fun getItemCount(): Int {
        return 0
     }

    override fun onBindViewHolder(p0: TimeHolder, p1: Int) {

    }

    class TimeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}