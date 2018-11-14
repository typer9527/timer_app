package com.yl.timer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.include_time_pick.*

class AddTaskActivity : AppCompatActivity(), View.OnClickListener {
    private val unitHour = 0
    private val unitMinute = 1
    private val unitSecond = 2
    private var currentHour = 0
    private var currentMinute = 0
    private var currentSecond = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        initViews()
    }

    private fun initViews() {
        tb_back.setNavigationOnClickListener { finish() }
        setTimeSpinner(spinner_hours, unitHour)
        setTimeSpinner(spinner_minutes, unitMinute)
        setTimeSpinner(spinner_seconds, unitSecond)
        btn_add_task.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_add_task -> {
                when {
                    et_task_name.text.isBlank() -> Toast.makeText(
                        this,
                        "Empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    getTimeInterval() == 0 -> Toast.makeText(this, "==0", Toast.LENGTH_SHORT).show()
                    else -> {
                        val newTask =
                            TaskEntity(
                                et_task_name.text.toString().trim(),
                                getTimeInterval().toLong(),
                                getPromptMode(),
                                getEnableLoop(),
                                true
                            )
                        Log.e("task", newTask.toString())
                    }
                }
            }
        }
    }

    private fun getEnableLoop(): Boolean {
        return cb_loop.isChecked
    }

    private fun getPromptMode(): Int {
        return if (rb_notification.isChecked) Constants.modeNotification
        else Constants.modeRing
    }

    private fun getTimeInterval(): Int {
        return currentHour * 60 * 60 * 1000 + currentMinute * 60 * 1000 + currentSecond * 1000
    }

    private fun setTimeSpinner(spinner: Spinner, timeUnit: Int) {
        val timeLength = ArrayList<String>()
        when (timeUnit) {
            unitHour -> {
                for (i in 0 until 24) timeLength.add(i.toString())
                spinner.adapter =
                        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
            }
            unitMinute -> {
                for (i in 0 until 60) timeLength.add(i.toString())
                spinner.adapter =
                        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
            }
            unitSecond -> {
                for (i in 0 until 60) timeLength.add(i.toString())
                spinner.adapter =
                        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
            }
        }
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
