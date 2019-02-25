package com.vehbiakdogan.todo.service;

import com.vehbiakdogan.todo.model.TodoItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface  TodoItemService {

    void addTodoItem(TodoItem todoItem);
    Optional<TodoItem> getTodoItem(Long todoItemId);
    List<TodoItem> getItemByTodoId(Long todoId);
    boolean deleteItem(Long todoItemId);
    void deleteItemByTodoId(Long todoId);
    boolean updateStatus(Long todoId,boolean status);
}
