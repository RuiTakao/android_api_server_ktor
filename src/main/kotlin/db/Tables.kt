package com.android.server.db

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

object DeviceData: Table("devices") {
    val id = integer("id").autoIncrement()
    val deviceId = varchar("device_id", 50)
    val deviceName = varchar("device_name", 50)
    val createdAt = varchar("created_at", 50).default("")
    override val primaryKey = PrimaryKey(Todos.id)
}