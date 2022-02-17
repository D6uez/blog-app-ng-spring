package io.david.springblogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.david.springblogbackend.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringBlogBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogBackendApplication.class, args);
	}

}
