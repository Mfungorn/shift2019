package com.example.app.core.model

class User (
    val id: Long,
    val login: String,
    val name: String,
    var icon: String,
    val email: String,
    var phone: String,
    var friends: ArrayList<User>
)