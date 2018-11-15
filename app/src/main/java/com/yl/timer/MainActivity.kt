package com.yl.timer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class MainActivity : AppCompatActivity(), View.OnClickListener {
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
        rv_list.adapter = TaskListAdapter(taskList)
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(this, AddTaskActivity().javaClass))
    }
}
