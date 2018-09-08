package com.trainings.rest.config;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Configuration
public class ValidationMessagesConfig {

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:locale/messages");
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultEncoding("UTF-8");
		// # -1 : never reload, 0 always reload
		source.setCacheSeconds(0);
		return source;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	/**
	 * Prints available locales. It is useful to set up appropriate
	 * messages.properties file name, e.g. messages_en_US.properties
	 * 
	 * en_US
	 * 
	 * en -> language; US -> country
	 * 
	 */
	public static void main(String[] args) {
		Locale[] locales = Locale.getAvailableLocales();
		Arrays.sort(locales, (l1, l2) -> l1.toString().compareTo(l2.toString()));
		for (Locale l : locales) {
			System.out.println(l.toString());
		}
	}

}