package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping
    public List<User> list(@RequestParam(required = false) String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (name != null) {
            queryWrapper.like(User::getName, name);
        }

        return userService.list(queryWrapper);
    }

    @PutMapping
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    @DeleteMapping("{id}")
    public boolean remove(@PathVariable int id) {
        return userService.removeById(id);
    }

}
