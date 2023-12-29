package com.codicefun.wms.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void test() {
        System.out.println(service.getById(1));
    }

}
