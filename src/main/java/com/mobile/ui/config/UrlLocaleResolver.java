package com.mobile.ui.config;
import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UrlLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String[] pathSegments = request.getRequestURI().split("/");
        if (pathSegments.length > 2) {
            String country = pathSegments[1];  // Assuming the country is the first segment
            String lang = pathSegments[2];  // Assuming the language is the second segment
            return new Locale.Builder().setLanguage(lang).setRegion(country).build();
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // Nothing needed here for URL-based locale switching
    }
}
