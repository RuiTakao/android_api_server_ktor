package com.android.server.domain.repository

import com.android.server.db.DatabaseFactory
import com.android.server.db.GetTodoResponse
import com.android.server.db.Todos

class TodoRepository {
    suspend fun getTodoList(): List<GetTodoResponse> = DatabaseFactory.dbQuery {
        Todos
            .select(
                Todos.id,
                Todos.title,
                Todos.memo,
                Todos.done,
                Todos.deviceId,
                Todos.createdAt,
            )
            .map {
                GetTodoResponse(
                    id = it[Todos.id],
                    title = it[Todos.title],
                    memo = it[Todos.memo],
                    done = it[Todos.done],
                    createdAt = it[Todos.createdAt],
                )
            }
    }
}