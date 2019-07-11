package com.example.app.core.model

data class UserSignUpPayload (
    var login: String,
    var username: String,
    var password: String,
    var email: String,
    var phone: String
)