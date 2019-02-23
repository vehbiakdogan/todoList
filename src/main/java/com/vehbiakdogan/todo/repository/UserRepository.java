package com.vehbiakdogan.todo.repository;

import com.vehbiakdogan.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query(value ="select u from User u   where u.username =?1 and u.password=?2")
    User usernamePasswordQuery(String username, String password);


    @Query(value ="select count(u) from User u   where u.username =?1")
    int usernameControl(String username);

}
