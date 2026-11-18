package com.lewis.spring_boot_practice.service;

import com.lewis.spring_boot_practice.dto.UpdateTodoRequest;
import com.lewis.spring_boot_practice.entity.Todo;
import com.lewis.spring_boot_practice.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        return todoRepository.save(todo);
    }

    public Page<Todo> getTodos(Boolean completed, Pageable pageable) {
        return completed != null
                ? todoRepository.findByCompleted(completed, pageable)
                : todoRepository.findAll(pageable);
    }

    public Todo updateTodo(Long id, UpdateTodoRequest request) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(request.getTitle());
                    todo.setCompleted(request.isCompleted());
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Todo not found: " + id
                ));
    }

    public void deleteTodo(Long id) {
        Todo todo =  todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Todo not found: " + id
                ));

        todoRepository.delete(todo);
    }
}
