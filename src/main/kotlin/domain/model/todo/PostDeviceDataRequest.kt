package com.android.server.domain.model.todo

import kotlinx.serialization.Serializable

@Serializable
data class PostDeviceDataRequest(
    val deviceId: String,
    val deviceName: String,
)