package com.ms.reggie.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 配置MP的分页插件
 * </p>
 *
 * @author SerMs
 * @date 2022/5/7 16:26
 */

@Configuration
public class MyBatisPlusConfig {
    /**
     * 新版分页插件设置
     */
    @Bean
    public MybatisPlusInterceptor getPaginationInnerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
