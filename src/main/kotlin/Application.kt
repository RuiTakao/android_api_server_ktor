package com.android.server

import com.android.server.data.TodoRepositoryImpl
import com.android.server.db.DatabaseFactory
import com.android.server.routes.todoRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.slf4j.event.Level

fun main() {
    EngineMain.main(emptyArray())
}

fun Application.module() {
    install(CallLogging) { level = Level.INFO }
    install(ContentNegotiation) { json() }

    DatabaseFactory.init()

    val repository = TodoRepositoryImpl()
    todoRoutes(repository)
}
