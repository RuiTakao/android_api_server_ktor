package com.android.server.di

import com.android.server.data.TodoRepositoryImpl
import com.android.server.domain.repository.TodoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TodoRepository> { TodoRepositoryImpl() }
}