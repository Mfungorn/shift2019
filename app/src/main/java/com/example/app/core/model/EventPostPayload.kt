package com.example.app.core.model

import java.util.*

data class EventPostPayload (
    var author: User?,
    var title: String,
    var about: String,
    var latitude: Double,
    var longitude: Double,
    var date: Date,
    var members: ArrayList<User>,
    var expenses: ArrayList<Expense>
)