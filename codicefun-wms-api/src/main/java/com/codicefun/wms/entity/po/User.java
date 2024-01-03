package com.codicefun.wms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Integer age;

    private Integer sex;

    private String phone;

    // 角色 id，0超级管理员，1管理员，2普通用户
    private Integer role;

    private String isValid;

}
