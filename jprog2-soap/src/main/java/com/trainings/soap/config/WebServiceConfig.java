package com.trainings.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


/**
 * 
 * @author Pavel Šeda
 *
 *         Our configuration class note @EnableWs for the usage of Spring-WS
 *         http://docs.spring.io/spring-ws/docs/current/api/org/springframework/ws/config/annotation/EnableWs.html
 */
@Configuration
@EnableWs
@ComponentScan("com.trainings.soap")
public class WebServiceConfig extends WsConfigurerAdapter {

	/**
	 * Creation of the MessageDispatcherServlet, note that it is different from a
	 * DispatcherServlet see
	 * http://docs.spring.io/spring-ws/site/reference/html/server.html in particular
	 * if you need to use it in a standard DispatcherServlet (section 5.3.2)
	 *
	 * @param applicationContext
	 * @return
	 */
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "users")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UsersPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://soap.trainings.com/entities/users");
		wsdl11Definition.setSchema(usersSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema usersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("users.xsd"));
	}

}
