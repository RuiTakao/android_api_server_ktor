package com.android.server.data

import com.android.server.db.Todos
import com.android.server.domain.model.todo.CreateTodoRequest
import com.android.server.domain.model.todo.GetTodoResponse
import com.android.server.domain.model.todo.UpdateTodoDoneRequest
import com.android.server.domain.model.todo.UpdateTodoRequest
import com.android.server.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

internal class TodoRepositoryImpl : TodoRepository {
    private fun row(row: ResultRow) = GetTodoResponse(
        id = row[Todos.id],
        title = row[Todos.title],
        memo = row[Todos.memo],
        done = row[Todos.done],
        deviceId = row[Todos.deviceId],
        createdAt = row[Todos.createdAt],
    )

    private suspend fun <T> db(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {
            addLogger(StdOutSqlLogger)
            block()
        }

    override suspend fun getTodoList(deviceId: String): List<GetTodoResponse> = db {
        if (deviceId.isBlank()) return@db emptyList()
        Todos.selectAll().where { Todos.deviceId eq deviceId }.map(::row)
    }

    override suspend fun getTodo(id: Int, deviceId: String): GetTodoResponse? = db {
        if (deviceId.isBlank()) return@db null
        Todos
            .selectAll().where { Todos.id eq id and (Todos.deviceId eq deviceId) }
            .limit(1)
            .singleOrNull()
            ?.let { row(it) }
    }

    override suspend fun create(request: CreateTodoRequest): Int = db {
        Todos.insert {
            it[title] = request.title
            it[memo] = request.memo
            it[done] = false
            it[deviceId] = request.deviceId
            it[createdAt] = request.createdAt
            it[updatedAt] = request.createdAt
        } get Todos.id
    }

    override suspend fun update(id: Int, request: UpdateTodoRequest): Int = db {
        Todos.update({ Todos.id eq id and (Todos.deviceId eq request.deviceId) }) {
            it[title] = request.title
            it[memo] = request.memo
            it[updatedAt] = request.updatedAt
        }
    }

    override suspend fun updateDone(id: Int, request: UpdateTodoDoneRequest): Int = db {
        Todos.update({ Todos.id eq id and (Todos.deviceId eq request.deviceId) }) {
            it[done] = request.done
            it[updatedAt] = request.updatedAt
        }
    }

    override suspend fun delete(id: Int): Int = db {
        Todos.deleteWhere { Todos.id eq id }
    }
}