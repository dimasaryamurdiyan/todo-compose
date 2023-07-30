package com.singaludra.todoapp.data

import com.singaludra.todoapp.data.source.DataSource
import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import com.singaludra.todoapp.data.source.local.entity.toToDo
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.domain.model.toToDoEntity
import com.singaludra.todoapp.domain.repository.IToDoRepository
import java.util.Date
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val localDataSource: DataSource.Local
): IToDoRepository {
    override suspend fun getToDoEntries(): List<ToDo> = localDataSource.getToDoEntries().map {
        it.toToDo()!!
    }

    override suspend fun getToDoEntry(id: Int): ToDo = localDataSource.getToDoEntry(id).toToDo()!!

    override suspend fun addToDoEntry(title: String, desc: String, date: Date) = localDataSource.addToDoEntry(title, desc, date)

    override suspend fun deleteToDoEntry(toDoEntry: ToDo) = localDataSource.deleteToDoEntry(toDoEntry.toToDoEntity()!!)

    override suspend fun editToDoEntry(toDoEntry: ToDo) = localDataSource.editToDoEntry(toDoEntry.toToDoEntity()!!)
    override suspend fun deleteCompletedToDo() = localDataSource.deleteCompletedTodo()
    override suspend fun deleteAllTodo() = localDataSource.deleteAllTodo()

}