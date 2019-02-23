package com.vehbiakdogan.todo;

import com.vehbiakdogan.todo.model.Todo;
import com.vehbiakdogan.todo.repository.TodoRepository;
import com.vehbiakdogan.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public void setTodoRepository(TodoRepository userRepository) {
        this.todoRepository = todoRepository;
    }



    @Override
    public void addTodo(Todo todo) {
        todoRepository.save(todo);

    }

    @Override
    public Todo getTodo(Long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if(!optionalTodo.isPresent()) {
            return null;
        }else {
            return optionalTodo.get();
        }
    }

    @Override
    public List<Todo> getAllTodo(Long userId) {
        List<Todo> todos = todoRepository.getUserAllTodo(userId);
        return todos;
    }
    @Override
    public boolean deleteTodo(Long todoId) {
         todoRepository.deleteById(todoId);
        return true;
    }
}
