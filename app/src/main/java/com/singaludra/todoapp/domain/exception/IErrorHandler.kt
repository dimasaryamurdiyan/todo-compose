package com.singaludra.todoapp.domain.exception

interface IErrorHandler {
    fun handleException(throwable: Throwable?): ErrorModel
}