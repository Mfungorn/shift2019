package com.example.app.core.model

import java.util.*
import kotlin.collections.ArrayList

data class Event (
    val id: Long,
    val author: User,
    var title: String,
    var about: String,
    var latitude: Double,
    var longitude: Double,
    var date: Date,
    var status: String,
    var members: ArrayList<Guest>,
    var expenses: ArrayList<Expense>
)