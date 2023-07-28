package com.singaludra.todoapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import com.singaludra.todoapp.data.source.local.room.dao.ToDoDao

@Database(
    entities = [ToDoEntity::class], version = 1
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}