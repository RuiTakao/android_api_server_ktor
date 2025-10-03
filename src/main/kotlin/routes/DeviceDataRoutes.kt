package com.android.server.routes

import com.android.server.domain.model.todo.PostDeviceDataRequest
import com.android.server.domain.repository.DeviceDataRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import io.ktor.server.application.log


fun Application.toDeviceDataRoutes() {
    val repository by inject<DeviceDataRepository>()

    routing {
        route("/devices") {
            post("/post") {
                val request = call.receive<PostDeviceDataRequest>()
                log.info("request: $request")
                repository.postDeviceData(request)
                call.respond(HttpStatusCode.Created)
            }
        }
    }
}