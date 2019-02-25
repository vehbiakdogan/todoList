package com.vehbiakdogan.todo.repository;

import com.vehbiakdogan.todo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TodoItemRepository  extends JpaRepository<TodoItem,Long> {

    @Query(value ="select t from TodoItem t   where t.todoId=?1")
    List<TodoItem> getItemsByTodoId(Long todoId);

    @Transactional
    @Modifying
    @Query(value ="delete from TodoItem t  where t.todoId=?1")
    void deleteByTodoId(Long todoId);

    @Transactional
    @Modifying
    @Query(value ="update TodoItem t  set t.status=?2 where t.itemId=?1")
    void updateStatus(Long todoId,boolean status);

}
