package com.example.app.core.model

data class Expense (
    val id: Long,
    val event: Event,
    var name: String,
    var cost: Double
)