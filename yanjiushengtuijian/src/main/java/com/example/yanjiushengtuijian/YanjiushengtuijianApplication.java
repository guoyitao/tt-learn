package com.example.yanjiushengtuijian;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 程序启动类
 */
@SpringBootApplication
@MapperScan("com.example.yanjiushengtuijian.mapper") //mybayts扫描
public class YanjiushengtuijianApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(YanjiushengtuijianApplication.class, args);
    }

}
