package com.codicefun.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codicefun.wms.entity.po.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectByAge(int age);

}
