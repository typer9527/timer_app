package com.yl.timer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class MainActivity : AppCompatActivity(), View.OnClickListener, OnItemClickListener,
    OnItemLongClickListener, OnCheckedChangeListener {
    private val taskList = mutableListOf<TaskEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 创建数据库
        LitePal.getDatabase()
        initViews()
        updateTaskList()
    }

    private fun updateTaskList() {
        taskList.addAll(LitePal.findAll<TaskEntity>())
    }

    private fun initViews() {
        fab_add.setOnClickListener(this)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        val adapter = TaskListAdapter(taskList)
        adapter.onItemClickListener = this
        adapter.onItemLongClickListener = this
        adapter.onCheckedChangeListener = this
        rv_list.adapter = adapter
    }

    override fun onCheckedChange(checked: Boolean, position: Int) {
        Log.e("onCheckedChange", checked.toString())
    }

    override fun onItemLongClick(position: Int) {
        Log.e("onItemLongClick", taskList[position].toString())
    }

    override fun onItemClick(position: Int) {
        Log.e("onItemClick", taskList[position].toString())
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(this, AddTaskActivity().javaClass))
    }
}
