package com.android.server.domain.repository

import com.android.server.domain.model.todo.GetTodoResponse

interface TodoRepository {
    suspend fun getTodoList(deviceId: String): List<GetTodoResponse>
    suspend fun getTodo(id: Int, deviceId: String): GetTodoResponse?
}