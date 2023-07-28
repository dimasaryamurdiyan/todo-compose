package com.singaludra.todoapp.di

import android.content.Context
import androidx.room.Room
import com.singaludra.todoapp.data.source.local.room.AppDatabase
import com.singaludra.todoapp.data.source.local.room.dao.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideToDoDao(
        appDatabase: AppDatabase
    ): ToDoDao =
        appDatabase.toDoDao()


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "To Do Entries"
        ).build()
    }
}