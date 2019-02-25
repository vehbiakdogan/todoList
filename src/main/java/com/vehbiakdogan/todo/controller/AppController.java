package com.vehbiakdogan.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/")
    public Map serviceStatus() {

         Map status = new HashMap();
        status.clear();
        status.put("status", "success");
        status.put("code", HttpStatus.OK);
        status.put("message", "Api Is Working!");
        return status;
    }
}
