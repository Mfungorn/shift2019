package com.example.app.core.model

data class UserSignUpPayload (
    val login: String,
    val name: String,
    val password: String
)