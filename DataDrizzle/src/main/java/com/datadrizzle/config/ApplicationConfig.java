package com.datadrizzle.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.datadrizzle")
@ComponentScan("org.teiid.webui")
public class ApplicationConfig {

}
