package com.android.server.domain.repository

import com.android.server.domain.model.todo.GetTodoResponse

interface TodoRepository {
    suspend fun getTodoList(): List<GetTodoResponse>
}