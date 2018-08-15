package com.trainings.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.trainings.jpa"})
@EntityScan(basePackages = {"com.trainings.jpa.model"})
@EnableJpaRepositories(basePackages = {"com.trainings.jpa.repository"})
public class PersistenceConfig {

}
