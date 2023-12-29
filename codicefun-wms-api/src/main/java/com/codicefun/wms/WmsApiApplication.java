package com.codicefun.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.codicefun.wms.mapper")
public class WmsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApiApplication.class, args);
    }

}
