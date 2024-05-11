package model

import java.time.LocalDateTime
import java.util.UUID


data class TodoItemModel(
    var description: String,
    var created: LocalDateTime,
    var done: Boolean,
    var doneTime: LocalDateTime?,
    val id: String = UUID.randomUUID().toString(),
)
