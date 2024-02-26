package com.endava

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredMemberProperties

class DataManager {

    private val log: Logger = LoggerFactory.getLogger(DataManager::class.java)
    private var books = ArrayList<Book>()

    private fun getId() = books.size.toString()

    init {
        books.add(Book(getId(), "How to grow apples", "Mr. Appleton", 100.0f))
        books.add(Book(getId(), "How to grow oranges", "Mr. Orange", 90.0f))
        books.add(Book(getId(), "How to grow lemons", "Mr. Lemon", 110.0f))
        books.add(Book(getId(), "How to grow pineapples", "Mr. Pineapple", 100.0f))
        books.add(Book(getId(), "How to grow pears", "Mr. Pears", 110.0f))
        books.add(Book(getId(), "How to grow coconuts", "Mr. Coconut", 130.0f))
        books.add(Book(getId(), "How to grow bananas", "Mr. Appleton", 120.0f))
    }

    fun addBook(book: Book): Book {
        books.add(book)

        return book
    }

    fun updateBook(id: String, book: Book): Book? {
        val foundBook = books.find { it.id == id }

        foundBook?.title = book.title
        foundBook?.author = book.author
        foundBook?.price = book.price

        return foundBook
    }

    fun deleteBook(book: Book): Book? {
        val foundBook = books.find { it.id == book.id }
        books.remove(foundBook)

        return foundBook
    }

    fun deleteBook(bookId: String): Book? {
        val foundBook = books.find { it.id == bookId }
        books.remove(foundBook)

        return foundBook
    }

    fun getAllBooks(): List<Book> = books

    fun getSortedBooks(sortedBy: String, asc: Boolean): List<Book> {
        val member = Book::class.declaredMemberProperties.find { it.name == sortedBy }

        if (member == null) {
            log.info("The field to sort does not exist!")
            return getAllBooks()
        }

        if (asc) {
            return getAllBooks().sortedBy { member.get(it).toString() }
        }
        return getAllBooks().sortedByDescending { member.get(it).toString() }
    }
}