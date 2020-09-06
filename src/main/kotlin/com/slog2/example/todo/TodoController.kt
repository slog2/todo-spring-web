package com.slog2.example.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TodoController {
  @Autowired
  private lateinit var todoService: TodoService

  @GetMapping(value = ["/hello"])
  fun hello() = "Hello Spring Boot"

  @GetMapping(value = ["/todo/{id}"])
  fun getTodo(@PathVariable id: Int): ResponseEntity<Any> {
    val todo = todoService.getTodo(id)
    return if (todo != null)
      ResponseEntity(todo, HttpStatus.OK)
    else
      // 비즈니스 예외 처리
      ResponseEntity(ErrorResponse("Customer Not Found", "customer '$id' not found"), HttpStatus.NOT_FOUND)
  }

  @PostMapping(value = ["/todo"])
  fun createTodo(@RequestBody todo: Todo): ResponseEntity<Unit?> {
    todoService.createTodo(todo)
    return ResponseEntity<Unit?>(null, HttpStatus.CREATED)
  }

  @PutMapping(value = ["/todo/{id}"])
  fun updateTodo(@PathVariable id: Int, @RequestBody todo: Todo): ResponseEntity<Unit?> {
    var status = HttpStatus.NOT_FOUND
    if (todoService.getTodo(id) != null) {
      todoService.updateTodo(id, todo)
      status = HttpStatus.ACCEPTED
    }
    return ResponseEntity<Unit?>(null, status)
  }

  @DeleteMapping(value = ["/todo/{id}"])
  fun deleteTodo(@PathVariable id: Int): ResponseEntity<Unit?> {
    var status = HttpStatus.NOT_FOUND
    if (todoService.getTodo(id) != null) {
      todoService.deleteTodo(id)
      status = HttpStatus.ACCEPTED
    }
    return ResponseEntity<Unit?>(null, status)
  }

  @GetMapping(value = ["/todos"])
  fun getTodos(@RequestParam(required = false, defaultValue = "") nameFilter: String): List<Todo> {
    return todoService.searchTodos(nameFilter)
  }
}
