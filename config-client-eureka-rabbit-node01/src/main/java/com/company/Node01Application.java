package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Node01Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Node01Application.class).web(true).run(args);
	}

}
