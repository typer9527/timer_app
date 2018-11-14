package com.yl.timer

import org.litepal.crud.LitePalSupport

data class TaskEntity(
    var taskName: String,
    var timeInterval: Long,
    var promptMode: Int,
    var enableLoop: Boolean,
    var enable: Boolean
) : LitePalSupport() {
    override fun toString(): String {
        return taskName + ", " + timeInterval + ", " +
                promptMode + ", " + enableLoop + ", " + enable
    }
}
