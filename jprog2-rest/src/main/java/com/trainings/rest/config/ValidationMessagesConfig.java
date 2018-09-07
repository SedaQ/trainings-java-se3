package com.trainings.rest.config;

import java.util.Arrays;
import java.util.Locale;

import javax.validation.Validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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
	 * Prints available locales. It is usefull to set up appropriate
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

	/*
	 * @Bean public LocaleResolver localeResolver() { SessionLocaleResolver slr =
	 * new SessionLocaleResolver(); slr.setDefaultLocale(Locale.US); return slr; }
	 */

	/*
	 * @Bean public LocaleChangeInterceptor localeChangeInterceptor() {
	 * LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	 * lci.setParamName("lang"); return lci; }
	 * 
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(localeChangeInterceptor());
	 */

}
