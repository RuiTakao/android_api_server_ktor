package com.android.server.routes

import com.android.server.domain.model.todo.CreateTodoRequest
import com.android.server.domain.model.todo.GetTodoResponse
import com.android.server.domain.model.todo.UpdateTodoDoneRequest
import com.android.server.domain.model.todo.UpdateTodoRequest
import com.android.server.domain.repository.TodoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.todoRoutes() {
    val repository by inject<TodoRepository>()

    routing {
        route("/todos") {
            get("/todo_list") {
                val deviceId = call.request.queryParameters["deviceId"]
                val list: List<GetTodoResponse> = repository.getTodoList(deviceId ?: "")
                log.info("todoList: $list")
                call.respond(list)
            }

            get("/todo/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val deviceId = call.request.queryParameters["deviceId"]
                val todo: GetTodoResponse? = repository.getTodo(id ?: -1, deviceId ?: "")
                todo?.let {
                    call.respond(todo)
                }
            }

            post("/create") {
                val request = call.receive<CreateTodoRequest>()
                repository.create(request)
                call.respond(HttpStatusCode.Created)
            }

            put("/update/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val request = call.receive<UpdateTodoRequest>()
                repository.update(id ?: -1, request)
                call.respond(HttpStatusCode.Created)
            }

            put("/update_done/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val request = call.receive<UpdateTodoDoneRequest>()
                repository.updateDone(id ?: -1, request)
                call.respond(HttpStatusCode.Created)
            }

            delete("/delete/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                // TODO create repository
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}