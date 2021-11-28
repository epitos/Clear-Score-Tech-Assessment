package com.clearscoretechtest.data.model

import com.squareup.moshi.Json

data class CoachingSummary(
    @Json(name = "activeChat")
    val activeChat: Boolean,
    @Json(name = "activeTodo")
    val activeTodo: Boolean,
    @Json(name = "numberOfCompletedTodoItems")
    val numberOfCompletedTodoItems: Int,
    @Json(name = "numberOfTodoItems")
    val numberOfTodoItems: Int,
    @Json(name = "selected")
    val selected: Boolean
)