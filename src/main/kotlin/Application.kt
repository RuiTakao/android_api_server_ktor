package com.android.server

import com.android.server.di.appModule
import com.android.server.di.repositoryModule
import com.android.server.routes.toDeviceDataRoutes
import com.android.server.routes.todoRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.event.Level

fun main() {
    EngineMain.main(emptyArray())
}

fun Application.module() {
    install(CallLogging) { level = Level.INFO }
    install(ContentNegotiation) { json() }

    install(Koin) {
        slf4jLogger()
        modules(
            module { single<ApplicationEnvironment> { environment } },
            appModule,
            repositoryModule
        )
    }

    toDeviceDataRoutes()
    todoRoutes()
}
