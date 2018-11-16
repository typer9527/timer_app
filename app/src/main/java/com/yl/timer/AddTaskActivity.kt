package com.yl.timer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.include_time_pick.*
import org.litepal.LitePal
import org.litepal.extension.find

class AddTaskActivity : AppCompatActivity(), View.OnClickListener {
    private val unitHour = 0
    private val unitMinute = 1
    private val unitSecond = 2
    private var currentHour = 0
    private var currentMinute = 0
    private var currentSecond = 0
    private var theTask: TaskEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        initViews()
        initData(intent)
    }

    private fun initData(intent: Intent) {
        val id = intent.getLongExtra(Constants.KEY_TASK_ID, -1L)
        theTask = LitePal.find<TaskEntity>(id)
        if (theTask != null) {
            et_task_name.setText(theTask!!.taskName)
            spinner_hours.setSelection(theTask!!.hourId)
            spinner_minutes.setSelection(theTask!!.minuteId)
            spinner_seconds.setSelection(theTask!!.secondId)
            setPromptMode(theTask!!)
            cb_loop.isChecked = theTask!!.enableLoop
        }
    }

    private fun setPromptMode(task: TaskEntity) {
        when (task.promptMode) {
            Constants.MODE_NOTIFICATION -> rb_notification.isChecked = true
            Constants.MODE_RING -> rb_ring.isChecked = true
        }
    }

    private fun initViews() {
        tb_back.setNavigationOnClickListener { finish() }
        setTimeSpinner(spinner_hours, unitHour)
        setTimeSpinner(spinner_minutes, unitMinute)
        setTimeSpinner(spinner_seconds, unitSecond)
        btn_save_task.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_save_task -> {
                when {
                    et_task_name.text.isBlank() -> Toast.makeText(
                        this,
                        "Empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    getTimeInterval() == 0 -> Toast.makeText(this, "==0", Toast.LENGTH_SHORT).show()
                    else -> {
                        if (theTask == null)
                            theTask = newTask()
                        else
                            updateTask()
                        theTask!!.save()
                        finish()
                    }
                }
            }
        }
    }

    private fun newTask(): TaskEntity =
        TaskEntity(
            et_task_name.text.toString().trim(),
            getTimeInterval().toLong(),
            spinner_hours.selectedItemPosition,
            spinner_minutes.selectedItemPosition,
            spinner_seconds.selectedItemPosition,
            getPromptMode(),
            getEnableLoop(),
            true
        )

    private fun updateTask() {
        theTask!!.taskName = et_task_name.text.toString().trim()
        theTask!!.timeInterval = getTimeInterval().toLong()
        theTask!!.hourId = spinner_hours.selectedItemPosition
        theTask!!.minuteId = spinner_minutes.selectedItemPosition
        theTask!!.secondId = spinner_seconds.selectedItemPosition
        theTask!!.promptMode = getPromptMode()
        theTask!!.enableLoop = getEnableLoop()
        theTask!!.enable = true
    }

    private fun getEnableLoop() = cb_loop.isChecked

    private fun getPromptMode(): Int {
        return if (rb_notification.isChecked) Constants.MODE_NOTIFICATION
        else Constants.MODE_RING
    }

    private fun getTimeInterval() =
        currentHour * 60 * 60 * 1000 + currentMinute * 60 * 1000 + currentSecond * 1000

    private fun setTimeSpinner(spinner: Spinner, timeUnit: Int) {
        val timeLength = ArrayList<String>()
        when (timeUnit) {
            unitHour -> {
                for (i in 0 until 24) timeLength.add(i.toString())
            }
            unitMinute -> {
                for (i in 0 until 60) timeLength.add(i.toString())
            }
            unitSecond -> {
                for (i in 0 until 60) timeLength.add(i.toString())
            }
        }
        spinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (timeUnit) {
                    unitHour -> currentHour = timeLength[p2].toInt()
                    unitMinute -> currentMinute = timeLength[p2].toInt()
                    unitSecond -> currentSecond = timeLength[p2].toInt()
                }
            }
        }
    }
}
