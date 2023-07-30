package com.singaludra.todoapp.data.source

import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import java.util.Date

interface DataSource {
    interface Local {

        suspend fun getToDoEntries(): List<ToDoEntity>

        suspend fun getToDoEntry(id: Int): ToDoEntity

        suspend fun addToDoEntry(
            title: String,
            desc: String,
            date: Date
        )

        suspend fun deleteToDoEntry(toDoEntry: ToDoEntity)

        suspend fun editToDoEntry(toDoEntry: ToDoEntity)

        suspend fun deleteCompletedTodo()

    }

}