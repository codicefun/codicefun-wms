package com.codicefun.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.mapper.UserMapper;
import com.codicefun.wms.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getByAge(int age) {
        return userMapper.selectByAge(age);
    }

}
