package com.singaludra.todoapp.data

import com.singaludra.todoapp.data.source.DataSource
import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import com.singaludra.todoapp.domain.repository.IToDoRepository
import java.util.Date
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val localDataSource: DataSource.Local
): IToDoRepository {
    override suspend fun getToDoEntries(): List<ToDoEntity> = localDataSource.getToDoEntries()

    override suspend fun getToDoEntry(id: Int): ToDoEntity = localDataSource.getToDoEntry(id)

    override suspend fun addToDoEntry(title: String, desc: String, date: Date) = localDataSource.addToDoEntry(title, desc, date)

    override suspend fun deleteToDoEntry(toDoEntry: ToDoEntity) = localDataSource.deleteToDoEntry(toDoEntry)

    override suspend fun editToDoEntry(toDoEntry: ToDoEntity) = localDataSource.editToDoEntry(toDoEntry)

}