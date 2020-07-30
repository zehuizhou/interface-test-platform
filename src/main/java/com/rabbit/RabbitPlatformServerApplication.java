package com.rabbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAdminServer
@SpringBootApplication
@MapperScan("com.rabbit.dao")
@EnableScheduling
public class RabbitPlatformServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitPlatformServerApplication.class, args);
    }

    //为了解决tomcat报错问题
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
        return factory;
    }
//// 为了解决监控报错
//    @Bean({"threadPoolTaskExecutor", "webMvcAsyncTaskExecutor"})
//    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
//        return new ThreadPoolTaskExecutor();
//    }

}

