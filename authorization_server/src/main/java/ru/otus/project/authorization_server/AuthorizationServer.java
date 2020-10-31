package ru.otus.project.authorization_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigurationProperties
@SpringBootApplication
@EnableEurekaClient
public class AuthorizationServer {
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServer.class, args);
	}
}
