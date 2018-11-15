package com.yl.timer

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yl.timer.TaskListAdapter.TaskHolder
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListAdapter(var list: List<TaskEntity>) : RecyclerView.Adapter<TaskHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskHolder {
        val inflate = LayoutInflater.from(p0.context).inflate(R.layout.item_task, p0, false)
        return TaskHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskHolder, p1: Int) {
        holder.itemView.tv_name.text = list[p1].taskName
        holder.itemView.tv_des.text = list[p1].timeInterval.toString()
        holder.itemView.switch_enable.isChecked = list[p1].enable
        holder.itemView.switch_enable.setOnCheckedChangeListener { _, p1 ->
            Log.e("list", p1.toString())
        }
    }

    class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}