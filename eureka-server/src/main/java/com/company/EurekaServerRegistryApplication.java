package com.company;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerRegistryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServerRegistryApplication.class).web(true).run(args);
	}

}
