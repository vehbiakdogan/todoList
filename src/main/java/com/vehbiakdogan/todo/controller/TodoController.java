package com.vehbiakdogan.todo.controller;

import com.vehbiakdogan.todo.model.Todo;
import com.vehbiakdogan.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    Map status = new HashMap();

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping("todo")
    public Map createTodo(@RequestBody Todo todo) {

        Date date = new Date();
        String strDateFormat = " dd/MM/YYYY HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        todo.setCreatedTime(dateFormat.format(date));
        todoService.addTodo(todo);
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "Todo Created Success!");
        return status;
    }

    @PutMapping("todo")
    public Map updateTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "Todo Updated Success!");
        return status;
    }
    @DeleteMapping("todo")
    public Map deleteTodo(@RequestBody Todo todo) {
        todoService.deleteTodo(todo.getId());
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "Todo Deleted Success!");
        return status;
    }


    @PostMapping("userTodo")
    public Map getAllTodo(@RequestBody Todo todo) {
        List<Todo> t = todoService.getAllTodo(todo.getUserId());

        status.clear();
        if (t == null) {
            status.put("status", "failure");
            status.put("message", "Todo Not Found");
            status.put("message", todo);
            status.put("code", HttpStatus.GONE);
        } else {
            status.put("status", "success");
            status.put("message", "User Todo List");
            status.put("code", HttpStatus.OK);
            status.put("todo", t);
        }
        return status;
    }

    @GetMapping("todo/{todoId}")
    public Map getTodo(@PathVariable(name = "todoId") Long todoId) {
        Todo t = todoService.getTodo(todoId);

        status.clear();
        if (t == null) {
            status.put("status", "failure");
            status.put("message", "Todo Not Found");
            status.put("error", HttpStatus.GONE);
        } else {
            status.put("status", "Succes");
            status.put("message", "Todo details");
            status.put("code", HttpStatus.OK);

            status.put("todo", t);
        }
        return status;
    }

}
