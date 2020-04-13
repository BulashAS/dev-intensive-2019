package ru.skillbranch.devintensive.models

import java.util.*

//Реализуй паттерн Factory с методом makeUser(fullName) принимающий в качесте аргумента полное имя пользователя и возвращающий экземпляр класса User
data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {
    /*    constructor(id: String, firstName: String?, lastName: String?) : this(
            id = id,
            firstName = firstName,
            lastName = lastName,
            avatar = null
        )

        constructor(id: String) : this(id, "John", "Doe $id")

        init {
            println("It's Alive")
        }

        fun printMe() = println(
            """
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
        """.trimIndent()
        )*/
    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            
        }
    }
}



