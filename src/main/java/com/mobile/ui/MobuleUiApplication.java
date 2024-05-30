package com.mobile.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MobuleUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobuleUiApplication.class, args);
	}

}
