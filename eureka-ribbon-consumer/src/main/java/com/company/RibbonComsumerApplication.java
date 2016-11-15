package com.company;

import com.company.web.util.ApplicationSupport;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by feel on 2016/10/23.
 * link: http://projects.spring.io/spring-cloud/spring-cloud.html#spring-cloud-ribbon
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker //  注解开启断路器功能
@ComponentScan(value = "com.company")
public class RibbonComsumerApplication {

	private static final Logger logger = LoggerFactory.getLogger(RibbonComsumerApplication.class);


	public static void main(String[] args) throws Exception {

		SpringApplication.run(RibbonComsumerApplication.class, args);

		RoundRobinRule ribbonRule = (RoundRobinRule) ApplicationSupport.getBeanByClass(RoundRobinRule.class);
		System.out.println("ribbonRule = " + ribbonRule);

	}
}