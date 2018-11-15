package com.yl.timer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yl.timer.TaskListAdapter.TaskHolder
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListAdapter(private var list: List<TaskEntity>) : RecyclerView.Adapter<TaskHolder>() {
    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null
    var onCheckedChangeListener: OnCheckedChangeListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskHolder {
        val inflate = LayoutInflater.from(p0.context).inflate(R.layout.item_task, p0, false)
        return TaskHolder(inflate)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskHolder, p1: Int) {
        holder.itemView.tv_name.text = list[p1].taskName
        holder.itemView.tv_des.text = list[p1].timeInterval.toString()
        holder.itemView.switch_enable.isChecked = list[p1].enable
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.adapterPosition)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.onItemLongClick(holder.adapterPosition)
            true
        }
        holder.itemView.switch_enable.setOnCheckedChangeListener { _, checked ->
            onCheckedChangeListener?.onCheckedChange(checked, holder.adapterPosition)
        }
    }

    class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}