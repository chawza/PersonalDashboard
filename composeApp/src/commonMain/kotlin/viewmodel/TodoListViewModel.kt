package viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.TodoItemModel

class TodoListViewModel {
    private val _state = MutableStateFlow(listOf<TodoItemModel>())
    val state = _state.asStateFlow()

    fun deleteTodo(todo: TodoItemModel) {
        val todos = _state.value.toMutableList()
        val deleted = todos.remove(todo)
        if (!deleted) {
            throw IllegalStateException("Unable to delete todo")
        }
        _state.update {
            todos.toList()
        }
    }

    fun addTodo(todo: TodoItemModel) {
        val todos = _state.value.toMutableList()
        todos.add(todo)

        _state.update {
            todos.toList()
        }
    }
}