package com.example.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 使用方式见junit，sql的where条件后面一定要接分表的字段
 */
@MapperScan(basePackages = "com.example.sharding.mapper")
@SpringBootApplication
public class ShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }

}
