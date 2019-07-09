package com.example.app.core.model

data class Guest (
    val user: User,
    val status: String,
    var paid: Double,
    var total: Double
)