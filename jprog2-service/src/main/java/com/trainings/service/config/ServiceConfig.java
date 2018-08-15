package com.trainings.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.trainings.jpa.config.PersistenceConfig;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Configuration
@Import(PersistenceConfig.class)
@ComponentScan(basePackages = { "com.trainings.service.impl", "com.trainings.service.iface" })
public class ServiceConfig {

}
