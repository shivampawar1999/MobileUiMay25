package com.mobile.ui.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.ui.service.FragmentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FragmentExistenceInterceptor implements HandlerInterceptor {

	private final FragmentService fragmentService;

	@Autowired
	public FragmentExistenceInterceptor(FragmentService fragmentService) {
		this.fragmentService = fragmentService;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			boolean footerExists = fragmentService.fragmentExists("fragments/footer");
			modelAndView.addObject("footerExists", footerExists);
			// Add checks for other fragments as needed
		}
	}
}
