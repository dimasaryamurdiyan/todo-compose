package com.singaludra.todoapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.singaludra.todoapp.domain.model.ToDo
import java.util.Date

@Entity(tableName = "todo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "entry_id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "isDone") var isDone: Boolean = false
)

fun ToDoEntity?.toToDo(): ToDo? = this?.let {
    ToDo(id, title, desc, date, isDone)
}