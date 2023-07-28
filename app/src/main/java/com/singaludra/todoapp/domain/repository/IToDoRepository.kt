package com.singaludra.todoapp.domain.repository

import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import java.util.Date

interface IToDoRepository {
    suspend fun getToDoEntries(): List<ToDoEntity>

    suspend fun getToDoEntry(id: Int): ToDoEntity

    suspend fun addToDoEntry(
        title: String,
        desc: String,
        date: Date
    )

    suspend fun deleteToDoEntry(toDoEntry: ToDoEntity)

    suspend fun editToDoEntry(toDoEntry: ToDoEntity)
}