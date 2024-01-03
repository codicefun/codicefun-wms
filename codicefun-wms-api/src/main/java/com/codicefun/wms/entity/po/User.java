package com.codicefun.wms.entity.po;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Integer age;

    private Integer sex;

    private String phone;

    // 角色 id，0超级管理员，1管理员，2普通用户
    private Integer role;

    private Integer state;

    private Integer isDeleted;

}
