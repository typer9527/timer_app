package com.yl.timer

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

class TestJava {
    fun test(activity: Activity) {
        val spinner = Spinner(activity)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {

            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
    }
}
