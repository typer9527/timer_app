package com.yl.timer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_no_task.*
import org.litepal.LitePal
import org.litepal.extension.delete
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

    override fun onResume() {
        super.onResume()
        updateTaskList()
    }

    private fun updateTaskList() {
        if (taskList.isNotEmpty()) taskList.clear()
        taskList.addAll(LitePal.findAll<TaskEntity>())
        rv_list.adapter?.notifyDataSetChanged()
        checkEmptyList()
    }

    private fun checkEmptyList() {
        cl_no_data.visibility = if (taskList.size > 0) View.GONE else View.VISIBLE
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
        showPopMenu(position)
    }

    private fun showPopMenu(position: Int) {
        val menu = PopupMenu(this, rv_list.getChildAt(position))
        menu.menuInflater.inflate(R.menu.menu_pop, menu.menu)
        menu.gravity = Gravity.END
        menu.setOnMenuItemClickListener { p0 ->
            when (p0?.itemId) {
                R.id.item_edit -> {
                    onItemClick(position)
                    true
                }
                R.id.item_delete -> {
                    showDeleteSnack(position)
                    true
                }
                else -> false
            }
        }
        menu.show()
    }

    private fun showDeleteSnack(position: Int) {
        val task = taskList[position]
        taskList.removeAt(position)
        rv_list.adapter?.notifyDataSetChanged()
        Snackbar.make(
            rv_list.getChildAt(position),
            getString(R.string.label_task_deleted),
            Snackbar.LENGTH_LONG
        ).setAction(getString(R.string.label_undo)) {
            taskList.add(position, task)
            rv_list.adapter?.notifyDataSetChanged()
        }
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    task.id?.let { LitePal.delete<TaskEntity>(it) }
                }
            }).show()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, AddTaskActivity().javaClass)
        intent.putExtra(Constants.KEY_TASK_ID, taskList[position].id)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(this, AddTaskActivity().javaClass))
    }
}

