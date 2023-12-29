package com.codicefun.wms.entity.po;

import lombok.Data;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private int age;
    private int sex;
    private String phone;
    private int role;
    private String isValid;

}
