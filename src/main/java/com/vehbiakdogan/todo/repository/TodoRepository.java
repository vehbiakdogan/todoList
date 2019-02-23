package com.vehbiakdogan.todo.repository;

import com.vehbiakdogan.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query(value ="select t from Todo t   where t.userId=?1")
    List<Todo> getUserAllTodo(Long userId);
}
