package com.vehbiakdogan.todo.service;

import com.vehbiakdogan.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    void addTodo(Todo todo);
    Todo getTodo(Long todoId);
    List<Todo> getAllTodo(Long userId);
    boolean deleteTodo(Long todoId);
}
