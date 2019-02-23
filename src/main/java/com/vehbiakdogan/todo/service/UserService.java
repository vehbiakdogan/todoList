package com.vehbiakdogan.todo.service;

import com.vehbiakdogan.todo.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface UserService  {
    void saveUser(User u);
    User getUser(Long id);
    User getUser(String username, String password);
    boolean usernameControl(String username);



}
