package com.singaludra.todoapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singaludra.todoapp.data.Resource
import com.singaludra.todoapp.domain.model.ToDo
import com.singaludra.todoapp.domain.usecases.AddToDoUseCase
import com.singaludra.todoapp.domain.usecases.DeleteAllCompletedToDoUseCase
import com.singaludra.todoapp.domain.usecases.DeleteAllTodoUseCase
import com.singaludra.todoapp.domain.usecases.DeleteToDoUseCase
import com.singaludra.todoapp.domain.usecases.EditToDoUseCase
import com.singaludra.todoapp.domain.usecases.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getAllToDoUseCase: GetAllToDoUseCase,
    private val editToDoUseCase: EditToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val addToDoUseCase: AddToDoUseCase,
    private val deleteAllCompletedToDoUseCase: DeleteAllCompletedToDoUseCase,
    private val deleteAllTodoUseCase: DeleteAllTodoUseCase
): ViewModel() {
    private val _toDoEntries =
        MutableStateFlow<Resource<List<ToDo>>>(Resource.Loading)
    val toDoEntries: StateFlow<Resource< List<ToDo>>> get() = _toDoEntries.asStateFlow()

    var menuExpanded: Boolean by mutableStateOf(false)
        private set

    init {
        getToDoEntries()
    }

    fun onMenuExpanded() {
        menuExpanded = true
    }

    fun onMenuCollapsed() {
        menuExpanded = false
    }

    private fun getToDoEntries() {
        viewModelScope.launch {
            _toDoEntries.value = Resource.Loading
            delay(500)
            _toDoEntries.value = getAllToDoUseCase(Unit)
        }

    }

    fun addToDoEntry(
        title: String,
        desc: String,
        date: Date
    ) {
        viewModelScope.launch {
            addToDoUseCase(
                ToDo(
                    0,
                    title, desc, date, false
                )
            )
            getToDoEntries()
        }

    }

    fun editToDoEntry(toDoEntry: ToDo) {
        viewModelScope.launch {
            toDoEntry.isDone = !toDoEntry.isDone
            editToDoUseCase(toDoEntry)
            getToDoEntries()
        }
    }


    fun deleteTodo(todoEntry: ToDo) {
        viewModelScope.launch {
            deleteToDoUseCase(todoEntry)
            getToDoEntries()
        }
    }

    fun deleteAllCompletedTodo(){
        viewModelScope.launch {
            deleteAllCompletedToDoUseCase(Unit)
            onMenuCollapsed()
            getToDoEntries()
        }
    }

    fun deleteAllTodo(){
        viewModelScope.launch {
            deleteAllTodoUseCase(Unit)
            onMenuCollapsed()
            getToDoEntries()
        }
    }
}