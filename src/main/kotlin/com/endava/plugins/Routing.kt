package com.endava.plugins

import com.endava.books
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError)
            throw cause
        }
    }

    routing {
        books()
        get("/") {
            call.respondText("Hello World!")
        }
        authenticate ("bookStoreAuth") {
            get("/api/tryauth") {
                val principal = call.principal<UserIdPrincipal>()
                call.respondText("Hello, ${principal?.name}!")
            }
        }
        staticResources("/static", "static")
    }
}
