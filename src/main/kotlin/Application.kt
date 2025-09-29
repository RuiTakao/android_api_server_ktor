package com.android.server

import com.android.server.db.DatabaseFactory
import com.android.server.db.GetTodoResponse
import com.android.server.domain.repository.TodoRepository
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.path
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.slf4j.event.Level

fun main() {
    EngineMain.main(emptyArray())
}

fun Application.module() {
    DatabaseFactory.init()

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        json()
    }

    val repository = TodoRepository()

    routing {
        get("/todos/todo_list") {
            val todoList: List<GetTodoResponse> = repository.getTodoList()
            log.info("todoList: $todoList")
            call.respond(todoList)
        }
    }
}
