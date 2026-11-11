package com.lewis.spring_boot_practice.controller;

import com.lewis.spring_boot_practice.dto.CreateTodoRequest;
import com.lewis.spring_boot_practice.dto.TodoResponse;
import com.lewis.spring_boot_practice.dto.UpdateTodoRequest;
import com.lewis.spring_boot_practice.entity.Todo;
import com.lewis.spring_boot_practice.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoResponse createTodo(@RequestBody CreateTodoRequest request) {
        Todo todo =  todoService.createTodo(request.getTitle());

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }

    @GetMapping
    public List<TodoResponse> readTodos() {
        return todoService.getTodos().stream()
                .map(todo -> new TodoResponse(
                        todo.getId(),
                        todo.getTitle(),
                        todo.isCompleted()
                ))
                .toList();
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        Todo todo =  todoService.updateTodo(id, request);

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
