package com.android.server

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