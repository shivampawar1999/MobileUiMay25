package com.mobile.ui.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SeoService {

	@Autowired
	private MessageSource seoMessageSource;
	@Autowired
	private LocaleResolver localeResolver;

	public String getTitleForRequest(HttpServletRequest request, String country) {
		Locale locale = localeResolver.resolveLocale(request);
		return seoMessageSource.getMessage("seo.title." + country, null, locale);
	}

	public String getDescriptionForRequest(HttpServletRequest request, String country) {
		Locale locale = localeResolver.resolveLocale(request);
		return seoMessageSource.getMessage("seo.description." + country, null, locale);
	}
}
