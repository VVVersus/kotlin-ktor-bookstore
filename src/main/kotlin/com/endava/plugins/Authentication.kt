package com.endava.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() {
    val users = listOf("shopper1", "shopper2", "shopper3")
    val admins = listOf("admin")
    install(Authentication) {
        basic("bookStoreAuth") {
            realm = "Book store"
            validate {
                if ((users.contains(it.name) || admins.contains(it.name)) && it.password == "password")
                    UserIdPrincipal(it.name)
                else
                    null
            }
        }
    }
}