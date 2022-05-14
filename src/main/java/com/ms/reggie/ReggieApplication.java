package com.ms.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author SerMs
 * @date 2022/5/6 23:53
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.ms.reggie.mapper")
@EnableTransactionManagement
@EnableCaching      //开启SpringCache注解方式缓存功能
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info("项目启动成功.....");
    }
}
