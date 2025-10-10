package com.android.server.data

import com.android.server.db.DeviceData
import com.android.server.domain.model.todo.PostDeviceDataRequest
import com.android.server.domain.repository.DeviceDataRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class DeviceDataRepositoryImpl: DeviceDataRepository {
    override suspend fun postDeviceData(request: PostDeviceDataRequest) {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        newSuspendedTransaction(Dispatchers.IO) {
            DeviceData.insert {
                it[deviceId] = request.deviceId
                it[deviceName] = request.deviceName
                it[createdAt] = now.format(formatter)
            } get DeviceData.id
        }
    }
}