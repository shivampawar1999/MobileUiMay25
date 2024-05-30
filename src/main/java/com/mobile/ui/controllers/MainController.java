package com.mobile.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mobile.ui.config.LocaleProperties;
import com.mobile.ui.service.SeoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	private static final String DEFAULT_COUNTRY = "us";
	private static final String DEFAULT_LANGUAGE = "en";

	private final LocaleProperties localeProperties;
	private final SeoService seoService;

	public MainController(LocaleProperties localeProperties, SeoService seoService) {
		this.localeProperties = localeProperties;
		this.seoService = seoService;
	}

	@GetMapping("/{country}/{lang}/")
	public String home(@PathVariable("country") String country, @PathVariable("lang") String lang,
			HttpServletRequest request, Model model) {

		String direction = localeProperties.getLanguageDirections().get(lang);
		model.addAttribute("direction", direction);
		model.addAttribute("currentCountry", country);
		model.addAttribute("currentLanguage", lang);
		model.addAttribute("pageTitle", seoService.getTitleForRequest(request, country));
		model.addAttribute("pageDescription", seoService.getDescriptionForRequest(request, country));

		return "index";
	}

	@GetMapping("/")
	public String redirectWithDefaultLocale() {
		return "redirect:/" + DEFAULT_COUNTRY + "/" + DEFAULT_LANGUAGE + "/";
	}

	// Additional mappings as needed
}
