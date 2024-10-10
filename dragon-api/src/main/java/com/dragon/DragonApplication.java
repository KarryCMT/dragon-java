package com.dragon;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author liaoshen
 */
@SpringBootApplication
@MapperScan("com.dragon.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ("com.dragon.client"))
public class DragonApplication {
    private static final Logger LOG = LoggerFactory.getLogger(DragonApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DragonApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
