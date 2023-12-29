package com.codicefun.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.wms.mapper.UserMapper;
import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
