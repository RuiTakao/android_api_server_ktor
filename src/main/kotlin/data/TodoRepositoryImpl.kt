package com.android.server.data

import com.android.server.db.Todos
import com.android.server.domain.model.todo.GetTodoResponse
import com.android.server.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

internal class TodoRepositoryImpl : TodoRepository {
    private fun row(row: ResultRow) = GetTodoResponse(
        id = row[Todos.id],
        title = row[Todos.title],
        memo = row[Todos.memo],
        done = row[Todos.done],
        createdAt = row[Todos.createdAt],
    )

    private suspend fun <T> db(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }

    override suspend fun getTodoList(): List<GetTodoResponse> = db {
        Todos.selectAll().map(::row)
    }
}