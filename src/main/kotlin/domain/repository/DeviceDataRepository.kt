package com.android.server.domain.repository

import com.android.server.domain.model.todo.PostDeviceDataRequest

interface DeviceDataRepository {
    suspend fun postDeviceData(request: PostDeviceDataRequest)
}