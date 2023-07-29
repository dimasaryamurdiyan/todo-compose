package com.singaludra.todoapp.domain.model

import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import java.util.Date

data class ToDo(
    val id: Int,
    val title: String,
    val desc: String,
    val date: Date,
    var isDone: Boolean = false
)

fun ToDo?.toToDoEntity(): ToDoEntity? = this?.let {
    ToDoEntity(id, title, desc, date, isDone)
}
