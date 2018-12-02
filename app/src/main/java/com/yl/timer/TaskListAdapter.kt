package com.yl.timer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yl.timer.Constants.LENGTH_HOUR
import com.yl.timer.Constants.LENGTH_MINUTE
import com.yl.timer.Constants.LENGTH_SECOND
import com.yl.timer.TaskListAdapter.TaskHolder
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListAdapter(private var list: List<TaskEntity>) : RecyclerView.Adapter<TaskHolder>() {
    private var mContext: Context? = null
    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null
    var onCheckedChangeListener: OnCheckedChangeListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskHolder {
        mContext = p0.context
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.item_task, p0, false)
        return TaskHolder(inflate)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskHolder, p1: Int) {
        holder.itemView.tv_name.text = list[p1].taskName
        val hours = list[p1].timeInterval / LENGTH_HOUR
        val minutes = list[p1].timeInterval % LENGTH_HOUR / LENGTH_MINUTE
        val seconds = list[p1].timeInterval % LENGTH_HOUR % LENGTH_MINUTE / LENGTH_SECOND
        holder.itemView.tv_des.text =
                mContext?.getString(R.string.label_time_interval, hours, minutes, seconds)
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