package com.codicefun.wms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void testSelectById() {
        System.out.println(mapper.selectById(1));
    }

    @Test
    void testSelectByAge() {
        System.out.println(mapper.selectByAge(18));
    }

}
