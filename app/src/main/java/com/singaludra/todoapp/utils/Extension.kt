package com.singaludra.todoapp.utils

import android.content.Context
import com.singaludra.todoapp.domain.model.ToDo
import java.util.Date

fun List<ToDo>.filterDate(
    date: Date? = null
): List<ToDo> {
    var list = mutableListOf<ToDo>()
    if (date != null) {
        list = this.filter { toDo ->
            toDo.date in DateUtils.atStartOfDay(date)..DateUtils.atEndOfDay(date)
        } as MutableList<ToDo>

    }
    return list.sortedBy { it.date }
}

fun Context.getAppName(): String {
    val stringId = applicationInfo.labelRes
    return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else getString(
        stringId
    )
}