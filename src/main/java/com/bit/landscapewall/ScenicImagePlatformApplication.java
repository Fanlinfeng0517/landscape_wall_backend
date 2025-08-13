package com.bit.landscapewall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bit.landscapewall.mapper")
public class ScenicImagePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenicImagePlatformApplication.class, args);
    }

}
