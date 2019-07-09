package com.example.app.core.model

import java.util.*
import kotlin.collections.ArrayList

data class EventPostPayload (
    val author: User,
    var title: String,
    var about: String,
    var latitude: Double,
    var longitude: Double,
    var date: Date,
    var members: ArrayList<User>,
    var expenses: ArrayList<Expense>
)