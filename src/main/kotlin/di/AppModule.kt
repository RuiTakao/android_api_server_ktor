package com.android.server.di

import com.android.server.db.DatabaseFactory
import org.koin.dsl.module

val appModule = module {
    single(createdAtStart = true) { DatabaseFactory.init() }
}