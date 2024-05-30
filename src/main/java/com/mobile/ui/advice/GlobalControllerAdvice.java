package com.mobile.ui.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mobile.ui.config.LocaleProperties;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	private LocaleProperties localeProperties;

	@ModelAttribute("localeData")
	public LocaleProperties addLocaleDataToModel() {
		return localeProperties;
	}
}
