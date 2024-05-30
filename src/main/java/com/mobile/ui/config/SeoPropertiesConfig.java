package com.mobile.ui.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class SeoPropertiesConfig {

	@Bean
	public MessageSource seoMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:seo");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
