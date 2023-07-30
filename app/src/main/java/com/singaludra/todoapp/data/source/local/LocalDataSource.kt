package com.singaludra.todoapp.data.source.local

import com.singaludra.todoapp.data.source.DataSource
import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import com.singaludra.todoapp.data.source.local.room.dao.ToDoDao
import java.util.Date
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val toDoDao: ToDoDao
): DataSource.Local{
    override suspend fun getToDoEntries(): List<ToDoEntity> {
        return toDoDao.getAllEntry()
    }

    override suspend fun getToDoEntry(id: Int): ToDoEntity {
        return toDoDao.getEntry(id)
    }

    override suspend fun addToDoEntry(title: String, desc: String, date: Date) {
        return toDoDao.insert(
            ToDoEntity(
                title = title,
                desc = desc,
                date = date
            )
        )
    }

    override suspend fun deleteToDoEntry(toDoEntry: ToDoEntity) = toDoDao.delete(toDoEntry)

    override suspend fun editToDoEntry(toDoEntry: ToDoEntity) = toDoDao.edit(toDoEntry)
    override suspend fun deleteCompletedTodo() = toDoDao.deleteAllCompletedTodo()

    override suspend fun deleteAllTodo() = toDoDao.deleteAllTodo()
}