package com.trainings.facade.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.trainings.service.config.ServiceConfig;


/**
 * 
 * @author Pavel Šeda
 *
 */
@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackages = { "com.trainings.facade.iface", "com.trainings.facade.impl",
		"com.trainings.facade.mapping" })
public class FacadeConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
