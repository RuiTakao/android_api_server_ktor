package com.android.server.domain.model.todo

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTodoDoneRequest(
    val done: Boolean,
    val updatedAt: String,
)
