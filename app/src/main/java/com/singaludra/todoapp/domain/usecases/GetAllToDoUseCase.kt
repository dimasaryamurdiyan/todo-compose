package com.singaludra.todoapp.domain.usecases

import com.singaludra.todoapp.data.source.local.entity.ToDoEntity
import com.singaludra.todoapp.domain.base.BaseUseCase
import com.singaludra.todoapp.domain.exception.IErrorHandler
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.domain.repository.IToDoRepository
import javax.inject.Inject

class GetAllToDoUseCase @Inject constructor(
    private val repository: IToDoRepository,
    errorHandler: IErrorHandler
): BaseUseCase<Unit, List<ToDo>>(errorHandler){
    override suspend fun execute(parameters: Unit): List<ToDo> {
        return repository.getToDoEntries()
    }

}