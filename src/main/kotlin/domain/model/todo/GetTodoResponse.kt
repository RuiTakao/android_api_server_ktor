package com.android.server.domain.model.todo

import kotlinx.serialization.Serializable

@Serializable
data class GetTodoResponse(
    val id: Int,
    val title: String,
    val memo: String,
    val done: Boolean,
    val deviceId: String,
    val createdAt: String,
)