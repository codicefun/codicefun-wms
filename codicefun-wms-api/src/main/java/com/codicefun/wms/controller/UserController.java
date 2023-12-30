package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
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
    public List<User> list(@RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
                           @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size,
                           User user) {
        IPage<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (user != null) {
            if (user.getName() != null) {
                queryWrapper.like(User::getName, user.getName());
            }
        }

        IPage<User> result = userService.page(page, queryWrapper);

        return result.getRecords();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable int id) {
        return userService.removeById(id);
    }

}
