package com.singaludra.todoapp.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.singaludra.todoapp.data.source.local.entity.ToDoEntity

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo")
    suspend fun getAllEntry(): List<ToDoEntity>

    @Query("SELECT * FROM todo WHERE entry_id = :id")
    suspend fun getEntry(id: Int): ToDoEntity

    @Insert
    suspend fun insert(toDoEntry: ToDoEntity)

    @Update
    suspend fun edit(toDoEntry: ToDoEntity)

    @Delete
    suspend fun delete(toDoEntry: ToDoEntity)
}