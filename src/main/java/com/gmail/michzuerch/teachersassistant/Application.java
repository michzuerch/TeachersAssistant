package com.gmail.michzuerch.teachersassistant;

import com.gmail.michzuerch.teachersassistant.app.security.SecurityConfiguration;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.UserRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.UserService;
import com.gmail.michzuerch.teachersassistant.ui.MainView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring boot web application initializer.
 */
@SpringBootApplication(scanBasePackageClasses = { SecurityConfiguration.class, MainView.class, Application.class,
		UserService.class }, exclude = ErrorMvcAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = { UserRepository.class })
@EntityScan(basePackageClasses = { User.class })
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}