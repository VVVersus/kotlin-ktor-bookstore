package com.endava

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.books() {
    val dataManager = DataManager()
    route("/book") {

        get("") {

            call.respond(dataManager.getAllBooks())
        }

        post("/{id}") {
            val id = call.parameters["id"].toString()
            val book = call.receive(Book::class)
            val updatedBook = dataManager.updateBook(id, book)

            call.respond { updatedBook }
        }

        put("") {
            val book = call.receive(Book::class)
            val newBook = dataManager.addBook(book)

            call.respond { newBook }
        }

        delete("/{id}") {
            val id = call.parameters["id"].toString()
            val deletedBook = dataManager.deleteBook(id)

            call.respond { deletedBook }
        }
    }
}