package com.android.server.domain.model.todo

import kotlinx.serialization.Serializable

@Serializable
data class CreateTodoRequest(
    val title: String,
    val memo: String,
    val deviceId: String,
    val createdAt: String,
)
