package com.trainings.rest.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

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

	// REST settings

	/**
	 * Provides localized messages.
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	@Primary
	public MappingJackson2HttpMessageConverter jacksonHTTPMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		return jsonConverter;
	}

}
