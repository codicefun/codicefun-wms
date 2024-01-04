package com.codicefun.wms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecordMapperTest {

    @Autowired
    private RecordMapper recordMapper;

    @Test
    void testSelectVOPage() {
        System.out.println(recordMapper.selectVOPage(new Page<>()).getRecords());
    }

}
