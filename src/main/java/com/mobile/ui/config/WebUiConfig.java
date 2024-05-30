package com.mobile.ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mobile.ui.interceptor.DeviceHandlerInterceptor;
import com.mobile.ui.interceptor.FragmentExistenceInterceptor;

@Configuration
public class WebUiConfig implements WebMvcConfigurer {

	private final FragmentExistenceInterceptor fragmentExistenceInterceptor;

	@Autowired
	public WebUiConfig(FragmentExistenceInterceptor fragmentExistenceInterceptor) {
		this.fragmentExistenceInterceptor = fragmentExistenceInterceptor;
	}

	@Autowired
	private DeviceHandlerInterceptor deviceHandlerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceHandlerInterceptor);
	}
}
