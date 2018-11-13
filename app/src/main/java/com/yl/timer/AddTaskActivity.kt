package com.yl.timer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.include_time_pick.*

class AddTaskActivity : AppCompatActivity() {
    private val unitHour = 0
    private val unitMinute = 1
    private val unitSecond = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        tb_back.setNavigationOnClickListener { finish() }
        setTimeSpinner(spinner_hours, unitHour)
        setTimeSpinner(spinner_minutes, unitMinute)
        setTimeSpinner(spinner_seconds, unitSecond)
    }

    private fun setTimeSpinner(spinner: Spinner, timeUnit: Int) {
        val timeLength = ArrayList<String>()
        when (timeUnit) {
            unitHour -> {
                for (i in 0 until 24) timeLength.add(i.toString())
                spinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
            }
            unitMinute -> {
                for (i in 0 until 60) timeLength.add(i.toString())
                spinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
            }
            unitSecond -> {
                for (i in 0 until 60) timeLength.add(i.toString())
                spinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeLength)
            }
        }
    }
}
