package com.android.server.di

import com.android.server.db.DatabaseFactory
import com.android.server.config.DbConfig
import com.android.server.config.provideDbConfig
import io.ktor.server.config.ApplicationConfig
import org.koin.dsl.module

val appModule = module {
    single { provideDbConfig(get<ApplicationConfig>()) }
    single(createdAtStart = true) { DatabaseFactory.init(get<DbConfig>()) }
}