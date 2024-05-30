package com.mobile.ui.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DeviceHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String deviceType = request.getHeader("User-Agent").contains("Mobi") ? "mobile" : "desktop";
		request.setAttribute("deviceType", deviceType);
		return true;
	}
}
