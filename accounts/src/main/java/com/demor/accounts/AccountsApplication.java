package com.demor.accounts;

import com.demor.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDto.class})
@RefreshScope
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice 'REST' API Documentation",
				description = "Accounts microservice RESTful API documentation for learning project.",
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
				description = "Accounts microservice RESP API documentation.",
				url = "https://www.dennismoradkhani.se"
		)

)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
