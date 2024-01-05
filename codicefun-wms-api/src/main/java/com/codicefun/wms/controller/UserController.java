package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.entity.qo.LoginQO;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.entity.vo.TokenVO;
import com.codicefun.wms.service.UserService;
import com.codicefun.wms.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseVO<User> save(@RequestBody User user) {
        return userService.save(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping
    public ResponseVO<PaginationVO<User>> list(
            @RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
            @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size,
            User user) {
        IPage<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDeleted, 0);

        if (user != null) {
            if (user.getState() != null) {
                queryWrapper.eq(User::getState, user.getState());
            }
            if (user.getRole() != null) {
                queryWrapper.eq(User::getRole, user.getRole());
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
        User user = userService.getById(id);
        user.setIsDeleted(1);

        return userService.updateById(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @PutMapping("/{id}/state")
    public ResponseVO<User> changeState(@PathVariable Long id) {
        User user = userService.getById(id);

        if (user == null) {
            return ResponseVO.fail();
        }

        Integer state = user.getState();
        if (state == 0) {
            user.setState(1);
        } else {
            user.setState(0);
        }

        return userService.updateById(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @PostMapping("/login")
    public ResponseVO<TokenVO> login(@RequestBody LoginQO loginQO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String username = loginQO.getUsername();
        String password = loginQO.getPassword();

        if (username != null && password != null) {
            queryWrapper.eq(User::getUsername, username)
                        .eq(User::getPassword, password)
                        .ne(User::getRole, 2)
                        .eq(User::getState, 0)
                        .eq(User::getIsDeleted, 0);
            User user = userService.getOne(queryWrapper);
            if (user != null) {
                String token = jwtUtil.generateToken(username);
                return ResponseVO.success(new TokenVO(token));
            }
        }

        return ResponseVO.fail();
    }

    @PostMapping("/register")
    public ResponseVO<Object> register(@RequestBody LoginQO loginQO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String username = loginQO.getUsername();
        String password = loginQO.getPassword();

        if (username != null && password != null) {
            queryWrapper.eq(User::getUsername, username);
            User user = userService.getOne(queryWrapper);
            if (user != null) {
                return ResponseVO.fail().message("用户已经存在了");
            }
            return userService.save(new User(username, password)) ? ResponseVO.success() : ResponseVO.fail()
                                                                                                     .message("创建用户失败");
        } else {
            return ResponseVO.fail().message("用户名和密码不能为空");
        }
    }

}
