package com.vehbiakdogan.todo;

import com.vehbiakdogan.todo.model.TodoItem;
import com.vehbiakdogan.todo.repository.TodoItemRepository;
import com.vehbiakdogan.todo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {


    @Autowired
    private TodoItemRepository todoItemRepository;

    public void setTodoItemRepository(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public void addTodoItem(TodoItem todoItem) {
        todoItemRepository.save(todoItem);

    }

    @Override
    public Optional<TodoItem> getTodoItem(Long todoItemId) {
        return todoItemRepository.findById(todoItemId);
    }

    @Override
    public List<TodoItem> getItemByTodoId(Long todoId) {
        return todoItemRepository.getItemsByTodoId(todoId);

    }

    @Override
    public boolean deleteItem(Long todoItemId) {
        if(todoItemRepository.existsById(todoItemId)) {
            todoItemRepository.deleteById(todoItemId);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void deleteItemByTodoId(Long todoId) {
         todoItemRepository.deleteByTodoId(todoId);
    }

    @Override
    public boolean updateStatus(Long todoId, boolean status) {
        Optional<TodoItem> optionalTodoItem= todoItemRepository.findById(todoId);
        if(optionalTodoItem.isPresent()) {
            if(optionalTodoItem.get().getAttachedId() == 0) {
                todoItemRepository.updateStatus(todoId,status);
                return true;
            }else {
                Optional<TodoItem> optionalTodoItem2= todoItemRepository.findById(optionalTodoItem.get().getAttachedId());
                if(optionalTodoItem2.get().isStatus()) {
                    todoItemRepository.updateStatus(todoId,status);
                    return true;
                }else {
                    return false;
                }
            }
        }else {
            return false;
        }

    }
}
