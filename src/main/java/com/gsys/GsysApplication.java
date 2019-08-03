package com.gsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.gsys.core.config.AppContext;

@SpringBootApplication
@EnableConfigurationProperties
public class GsysApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GsysApplication.class, args);
		AppContext.loadApplicationContext(ctx);
	}

}
