package com.singaludra.todoapp.domain.repository

import com.singaludra.todoapp.domain.model.ToDo
import java.util.Date

interface IToDoRepository {
    suspend fun getToDoEntries(): List<ToDo>

    suspend fun getToDoEntry(id: Int): ToDo

    suspend fun addToDoEntry(
        title: String,
        desc: String,
        date: Date
    )

    suspend fun deleteToDoEntry(toDoEntry: ToDo)

    suspend fun editToDoEntry(toDoEntry: ToDo)

    suspend fun deleteCompletedToDo()
}