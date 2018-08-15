package com.trainings.rest.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.trainings.facade.config.FacadeConfig;

/**
 * 
 * @author Pavel Šeda
 *
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@Import(FacadeConfig.class)
@ComponentScan(basePackages = { "com.trainings.rest" })
public class RestConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestConfig.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestConfig.class, args);
	}

}
