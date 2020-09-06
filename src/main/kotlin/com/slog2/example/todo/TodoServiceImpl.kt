package com.slog2.example.todo

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class TodoServiceImpl : TodoService {
  companion object {
    // demo 데이터
    val initialTodos = arrayOf(
      Todo(1, "kotlin study"),
      Todo(2, "pull up"),
      Todo(3, "early to bed")
    )
  }
  val todos = ConcurrentHashMap<Int, Todo>(initialTodos.associateBy(Todo::id))

  override fun getTodo(id: Int) = todos[id]

  override fun createTodo(todo: Todo) {
    todos[todo.id] = todo
  }

  override fun updateTodo(id: Int, todo: Todo) {
    deleteTodo(id)
    createTodo(todo)
  }

  override fun deleteTodo(id: Int) {
    todos.remove(id)
  }

  override fun searchTodos(nameFilter: String) =
    todos.filter {
      it.value.name.contains(nameFilter, true)
    }.map(Map.Entry<Int, Todo>::value).toList()
}
