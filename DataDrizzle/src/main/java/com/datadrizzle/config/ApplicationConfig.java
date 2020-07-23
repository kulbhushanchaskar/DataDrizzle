package com.datadrizzle.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.h2.server.web.WebServlet;

import com.datadrizzle.share.ApplicationConstants;

@Configuration
@ComponentScan("com.datadrizzle")
@ComponentScan("org.teiid.webui")
public class ApplicationConfig {

	/*@Bean
	public DataSource dataSource() {

		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL) // .H2 or .DERBY
				.addScript("db/sql/create-db.sql").addScript("db/sql/insert-data.sql").build();
		return db;
	}*/
	
	/*@Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }*/
	
	@Bean
	public DozerBeanMapper entityMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{ApplicationConstants.dozerBeanMappingXML}));
		return mapper;
	}

}
