package com.demor.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans Microservice 'REST' API Documentation",
				description = "Loans microservice RESTful API documentation for learning project.",
				version = "v1",
				contact = @Contact(
						name = "Dennis Moradkhani",
						email = "dennis@moradkhani.se",
						url = "https://www.dennismoradkhani.se"),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.dennismoradkhani.se")
		),
		externalDocs = @ExternalDocumentation(
				description = "Loans microservice RESP API documentation.",
				url = "https://www.dennismoradkhani.se"
		)

)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
