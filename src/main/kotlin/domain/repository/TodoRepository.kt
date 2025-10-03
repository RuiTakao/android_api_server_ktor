package com.android.server.domain.repository

import com.android.server.domain.model.todo.CreateTodoRequest
import com.android.server.domain.model.todo.GetTodoResponse
import com.android.server.domain.model.todo.UpdateTodoDoneRequest
import com.android.server.domain.model.todo.UpdateTodoRequest

interface TodoRepository {
    suspend fun getTodoList(deviceId: String): List<GetTodoResponse>
    suspend fun getTodo(id: Int, deviceId: String): GetTodoResponse?
    suspend fun create(request: CreateTodoRequest): Int
    suspend fun update(id: Int, request: UpdateTodoRequest): Int
    suspend fun updateDone(id: Int, request: UpdateTodoDoneRequest): Int
}