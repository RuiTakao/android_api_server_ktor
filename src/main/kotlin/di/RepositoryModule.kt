package com.android.server.di

import com.android.server.data.DeviceDataRepositoryImpl
import com.android.server.data.TodoRepositoryImpl
import com.android.server.domain.repository.DeviceDataRepository
import com.android.server.domain.repository.TodoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TodoRepository> { TodoRepositoryImpl() }
    single<DeviceDataRepository> { DeviceDataRepositoryImpl() }
}