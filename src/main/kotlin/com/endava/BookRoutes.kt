package com.endava

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get

@Resource("/list")
data class BookListResource(val sortBy: String = "title", val asc: Boolean = true)

fun Route.books() {
    val dataManager = DataManager()

    route("/book") {

        get<BookListResource> {
            call.respond(dataManager.getSortedBooks(it.sortBy, it.asc))
        }

        get {

            call.respond(dataManager.getAllBooks())
        }

        post("/{id}") {
            val id = call.parameters["id"].toString()
            val book = call.receive(Book::class)
            val updatedBook = dataManager.updateBook(id, book)

            call.respond { updatedBook }
        }

        put("/") {
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