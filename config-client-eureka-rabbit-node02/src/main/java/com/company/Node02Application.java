package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Node02Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Node02Application.class).web(true).run(args);
	}

}
