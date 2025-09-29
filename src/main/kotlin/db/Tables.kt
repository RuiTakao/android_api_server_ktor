package com.android.server.db

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Todos: Table("todos") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 50)
    val memo = text("memo")
    val done = bool("is_done").default(false)
    val deviceId = varchar("device_id", 50).default("")
    val createdAt = varchar("created_at", 50).default("")
    val updatedAt = varchar("updated_at", 50).default("")
    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class GetTodoResponse(
    val id: Int,
    val title: String,
    val memo: String,
    val done: Boolean,
    val createdAt: String,
)