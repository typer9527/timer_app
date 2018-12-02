package com.yl.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TimerTaskReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, intent: Intent?) {
        Log.e("TimerTaskReceiver", intent?.getStringExtra("key_tip_info"))
        Log.e("TimerTaskReceiver", "test")
    }
}