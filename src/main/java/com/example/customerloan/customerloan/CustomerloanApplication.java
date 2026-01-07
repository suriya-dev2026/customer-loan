package com.example.customerloan.customerloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
	title = "Customer Loan Rest Api",
	version = "1.0",
	description = "This is a REST API with rest end points for customerloan"),
	servers = {
		@Server(url= "http://localhost:8080/api",description = "local server")
	})
public class CustomerloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerloanApplication.class, args);
	}

}
