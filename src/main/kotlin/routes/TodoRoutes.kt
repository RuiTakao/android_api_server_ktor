package com.android.server.routes

import com.android.server.domain.model.todo.GetTodoResponse
import com.android.server.domain.repository.TodoRepository
import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.todoRoutes() {
    val repository by inject<TodoRepository>()

    routing {
        route("/todos") {
            get("/todo_list") {
                val list: List<GetTodoResponse> = repository.getTodoList()
                log.info("todoList: $list")
                call.respond(list)
            }
        }
    }
}