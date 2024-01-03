package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseVO<User> save(@RequestBody User user) {
        return userService.save(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @CrossOrigin
    @GetMapping
    public ResponseVO<PaginationVO<User>> list(
            @RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
            @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size,
            User user) {
        IPage<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (user != null) {
            if (user.getNickname() != null) {
                queryWrapper.like(User::getNickname, user.getNickname());
            }
        }

        IPage<User> result = userService.page(page, queryWrapper);

        return ResponseVO.page(result);
    }

    @GetMapping("/{id}")
    public ResponseVO<User> get(@PathVariable Integer id) {
        User user = userService.getById(id);

        return ResponseVO.success(user);
    }

    @PutMapping("/{id}")
    public ResponseVO<User> update(@RequestBody User user) {
        return userService.updateById(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<User> remove(@PathVariable int id) {
        return userService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
