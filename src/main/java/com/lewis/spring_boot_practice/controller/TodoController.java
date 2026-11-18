package com.lewis.spring_boot_practice.controller;

import com.lewis.spring_boot_practice.dto.CreateTodoRequest;
import com.lewis.spring_boot_practice.dto.TodoPageResponse;
import com.lewis.spring_boot_practice.dto.TodoResponse;
import com.lewis.spring_boot_practice.dto.UpdateTodoRequest;
import com.lewis.spring_boot_practice.entity.Todo;
import com.lewis.spring_boot_practice.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public TodoResponse createTodo(@Valid @RequestBody CreateTodoRequest request) {
        Todo todo =  todoService.createTodo(request.getTitle());

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }

    @GetMapping
    public TodoPageResponse readTodos(@RequestParam(required = false) Boolean completed, Pageable pageable) {
        Page<TodoResponse> result = todoService.getTodos(completed, pageable)
                .map(todo -> new TodoResponse(
                        todo.getId(),
                        todo.getTitle(),
                        todo.isCompleted()
                ));
        return new TodoPageResponse(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalPages()
        );
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable Long id, @Valid @RequestBody UpdateTodoRequest request) {
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
