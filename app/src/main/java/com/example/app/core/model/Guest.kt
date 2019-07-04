package com.example.app.core.model

data class Guest (
    val id: Long,
    var event: Event,
    val user: User,
    var paid: Double,
    var mustpay: Double,
    var status: String
)