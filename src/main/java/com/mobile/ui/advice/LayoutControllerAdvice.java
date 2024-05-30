package com.mobile.ui.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mobile.ui.service.FragmentService;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LayoutControllerAdvice {

	@Autowired
	private FragmentService fragmentService;

	@ModelAttribute
	public void addLayoutAttributes(HttpServletRequest request, Model model) {
		String deviceType = (String) request.getAttribute("deviceType");

		boolean deviceFooterExists = fragmentService.footerExistsForDevice(deviceType);
		boolean defaultFooterExists = fragmentService.defaultFooterExists();

		model.addAttribute("deviceType", deviceType);
		model.addAttribute("deviceFooterExists", deviceFooterExists);
		model.addAttribute("defaultFooterExists", defaultFooterExists);
	}
}
