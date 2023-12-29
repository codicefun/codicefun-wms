package com.codicefun.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.wms.entity.po.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getByAge(int age);

}
