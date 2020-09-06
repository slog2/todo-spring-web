package com.slog2.example.todo

interface TodoService {
  fun getTodo(id: Int) : Todo?
  fun createTodo(todo: Todo)
  fun updateTodo(id: Int, todo: Todo)
  fun deleteTodo(id: Int)
  fun searchTodos(nameFilter: String): List<Todo>
}
