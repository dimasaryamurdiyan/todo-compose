package com.singaludra.todoapp.di

import com.singaludra.todoapp.data.ErrorHandler
import com.singaludra.todoapp.data.ToDoRepository
import com.singaludra.todoapp.data.source.DataSource
import com.singaludra.todoapp.data.source.local.LocalDataSource
import com.singaludra.todoapp.data.source.local.room.dao.ToDoDao
import com.singaludra.todoapp.domain.exception.IErrorHandler
import com.singaludra.todoapp.domain.repository.IToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource
    ): IToDoRepository {
        return ToDoRepository(localDataSource)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        toDoDao: ToDoDao,
    ): DataSource.Local {
        return LocalDataSource(toDoDao)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }
}