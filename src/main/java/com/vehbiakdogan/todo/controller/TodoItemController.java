package com.vehbiakdogan.todo.controller;

import com.vehbiakdogan.todo.model.TodoItem;
import com.vehbiakdogan.todo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoItemController {


    @Autowired
    TodoItemService todoItemService;

    Map status = new HashMap();

    public void setTodoService(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }




    @PostMapping("todoItem")
    public Map createTodoItem(@RequestBody TodoItem todoItem) {

        todoItemService.addTodoItem(todoItem);
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "Todo Item Created Success!");
        return status;
    }

    @PutMapping("todoItem")
    public Map updateTodoItem(@RequestBody TodoItem todoItem) {

        todoItemService.addTodoItem(todoItem);
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "Todo Item Updated Success!");
        return status;
    }

    @PutMapping("todoItem/{itemId}")
    public Map updateTodoItemStatus(@PathVariable(name = "itemId") Long todoId,@RequestBody TodoItem todoItem) {

        status.clear();
        if(todoItemService.updateStatus(todoId,todoItem.isStatus())) {
            status.put("status", "success");
            status.put("code", HttpStatus.OK);
            status.put("message", "Todo Item Status Updated Success!");
        }else {
            status.put("status", "failure");
            status.put("code", HttpStatus.GONE);
            status.put("message", "Todo Item Status Updated Failure!");
        }


        return status;
    }



    @GetMapping("todoItem/{todoId}")
    public Map getUserTodos(@PathVariable(name = "todoId") Long todoId) {
        List<TodoItem> t = todoItemService.getItemByTodoId(todoId);

        status.clear();
        if (t == null) {
            status.put("status", "failure");
            status.put("message", "Item  Not Found");
            status.put("code", HttpStatus.GONE);
        } else {
            status.put("status", "success");
            status.put("message", "Todo Item List");
            status.put("todoId",todoId);
            status.put("count", t.size());
            status.put("code", HttpStatus.OK);
            status.put("todo", t);
        }
        return status;
    }


}
