package com.cpcompany.accounts;

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
			title = "Accounts microservice REST API documentation",
			description = "Eazybank Accounts microservice REST API documentation",
			version = "v1",
			contact = @Contact(
				name = "Chandan Patil",
				email = "9eG2U@example.com",
				url = "https://github.com/chandanpatil"
			),
			license = @License(
				name = "Apache 2.0",
				url = "https://www.apache.org/licenses/LICENSE-2.0"
			)
		),
		externalDocs =@ExternalDocumentation(
				description = "Eazybank Accounts microservice REST API documentation",
				url = "https://github.com/chandanpatil"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
