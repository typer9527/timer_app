package com.yl.timer

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

interface OnItemLongClickListener {
    fun onItemLongClick(position: Int)
}

interface OnCheckedChangeListener {
    fun onCheckedChange(checked: Boolean, position: Int)
}