package com.star.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 启动入口
 * //SpringBootApplication，排除自动注入数据源的配置（取消数据库配置 exclude = {DataSourceAutoConfiguration.class}
 *
 * @author zhengx
 * @date 2019/8/22
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.star.account.mapper")
@EnableCaching
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
