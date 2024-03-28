package com.szaiot.njg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication()
@MapperScan("com.szaiot.njg.dao")
@ServletComponentScan
public class CarServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	try {
    		SpringApplication.run(CarServerApplication.class, args);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CarServerApplication.class);
	}

}
