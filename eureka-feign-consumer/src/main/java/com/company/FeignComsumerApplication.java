package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(value = "com.company")
public class FeignComsumerApplication {

    private static final Logger logger = LoggerFactory.getLogger(FeignComsumerApplication.class);


    public static void main(String[] args) throws Exception {

        //开启监控 http://localhost:8080/health

        SpringApplication.run(FeignComsumerApplication.class, args);

    }
}
