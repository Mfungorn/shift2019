package com.example.app.core.model

class User (
    val login: String,
    val username: String,
    var icon: String,
    val email: String,
    var phone: String,
    var friends: ArrayList<User>
)