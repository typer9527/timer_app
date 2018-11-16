package com.yl.timer

import org.litepal.crud.LitePalSupport

data class TaskEntity(
    var taskName: String,
    var timeInterval: Long,
    var hourId: Int,
    var minuteId: Int,
    var secondId: Int,
    var promptMode: Int,
    var enableLoop: Boolean,
    var enable: Boolean
) : LitePalSupport() {
    var id: Long? = null
    override fun toString(): String {
        return taskName + ", " + timeInterval + ", " +
                promptMode + ", " + enableLoop + ", " + enable
    }
}
