package com.mobile.ui.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "language")
public class LocaleProperties {
	private Map<String, String> countries;
	private Map<String, String[]> countryLanguages;
	private Map<String, String> languageDirections;

	public Map<String, String[]> getCountryLanguages() {
		return countryLanguages;
	}

	public void setCountryLanguages(Map<String, String[]> countryLanguages) {
		this.countryLanguages = countryLanguages;
	}

	public Map<String, String> getLanguageDirections() {
		return languageDirections;
	}

	public void setLanguageDirections(Map<String, String> languageDirections) {
		this.languageDirections = languageDirections;
	}

	/**
	 * @return the countries
	 */
	public Map<String, String> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Map<String, String> countries) {
		this.countries = countries;
	}
}
