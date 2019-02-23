package com.vehbiakdogan.todo.controller;

import com.vehbiakdogan.todo.model.User;
import com.vehbiakdogan.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    Map status = new HashMap();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // create User
    @PostMapping("user")
    public Map createUser(@RequestBody User user) {

        status.clear();
        if(!userService.usernameControl(user.getUsername())) {
            userService.saveUser(user);
            status.put("status", "success");
            status.put("code", HttpStatus.OK);
            status.put("message", "User Created Success!");
        }else {
            status.put("status", "failure");
            status.put("code", HttpStatus.GONE);
            status.put("message", "username alredy exsist!");
        }

        return status;
    }


    // update user

    // create User
    @PutMapping("user")
    public Map UpdateUser(@RequestBody User user) {
        userService.saveUser(user);
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "User Updated Success!");
        return status;
    }


    // user Details
    @GetMapping("user/{userId}")
    public Map getUser(@PathVariable(name = "userId") Long userId) {
        User u = userService.getUser(userId);

        status.clear();

        if (u == null) {
            status.put("status", "failure");
            status.put("message", "User Not Found");
            status.put("code", HttpStatus.GONE);
        } else {
            status.put("status", "success");
            status.put("message", "User Details");
            status.put("user", u);
            status.put("code", HttpStatus.OK);
        }
        return status;

    }



    // setLogin Users
    @PostMapping("isLogin")
    public Map setLogin(@RequestBody User user) {

        User u =  userService.getUser(user.getUsername(),user.getPassword());
        status.clear();
        if(u == null) {
            status.put("status","failure");
            status.put("message","User Not Found");
            status.put("user",u);
            status.put("code",HttpStatus.GONE);
        }else {
            status.put("status","success");
            status.put("message","User Details");
            status.put("code", HttpStatus.OK);
            u.setPassword("");
            status.put("user", u);
        }

        return status;

    }


}
