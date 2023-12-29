package com.codicefun.wms.controller;

import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/age/{age}")
    public List<User> getByAge(@PathVariable int age) {
        return userService.getByAge(age);
    }

}
