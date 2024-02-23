package com.endava

data class Book(var id: String, var title: String, var author: String, var price: Float)
data class User(var id: String, var name: String, var username: String, var password: String)
data class ShoppingItems(var bookId: String, var quantity: Int)
data class ShoppingCart(var id: String, var userId: String, val items: ArrayList<ShoppingItems>)