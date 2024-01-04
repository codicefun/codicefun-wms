package com.codicefun.wms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoodsMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void testSelectById() {
        System.out.println(goodsMapper.selectById(1));
    }

    @Test
    void testSelectPage() {
        System.out.println(goodsMapper.selectPage(new Page<>()).getRecords());
    }

}
