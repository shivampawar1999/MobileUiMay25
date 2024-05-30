package com.mobile.ui.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FragmentControllers {
	@Value("${fragmentvontrollers.resttemplate.timeoutofSeconds}")
	private int restTemplateTimeout;

	private static final Logger logger = LoggerFactory.getLogger(FragmentControllers.class);

	private final ResourceLoader resourceLoader;

	@Autowired
	public FragmentControllers(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@GetMapping("/fragments/{country}/{lang}/{fragmentName}")
	@Cacheable(value = "menuCache", key = "#country + #lang + #fragmentName", unless = "#result == null")
	@ResponseBody
	public String getMenuHtml(@PathVariable("country") String country, @PathVariable("lang") String lang,
			@PathVariable("fragmentName") String fragmentName, HttpServletRequest request, Model model) {
		String defaultCountry = "ae";
		String defaultLang = "en";
		String baseUrl = "https://your-s3-bucket-url/";
		String basePath = "/staticTemplates/fragments/";// Update this path to your local folder

		String url = baseUrl + country + "/" + lang + "/" + fragmentName + ".html";

		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(restTemplateTimeout))
				.setReadTimeout(Duration.ofSeconds(restTemplateTimeout)).build();

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {

			try {
				// find same country default lang value
				url = baseUrl + country + "/" + defaultLang + "/" + fragmentName + ".html";
				ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
				return response.getBody();
			} catch (HttpClientErrorException.NotFound ex) {
				// If file not found, use default values
				url = baseUrl + defaultCountry + "/" + defaultLang + "/" + fragmentName + ".html";
				ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
				return response.getBody();
			}

		} catch (Exception e) {
			// Return empty string for any other errors
			/*
			 * logger.error( "An error occurred while fetching menu HTML for ::" + country +
			 * "/" + lang + "/" + fragmentName, e); e.printStackTrace();
			 */

			e.printStackTrace();
			String filePath = basePath + fragmentName + ".html";
			if (!Files.exists(Paths.get(filePath))) {
				try {
					Resource resource = resourceLoader.getResource("classpath:" + filePath);
					String fileContent = new String(Files.readAllBytes(resource.getFile().toPath()));
					return fileContent;
				} catch (IOException ef) {
					// Log the error
					ef.printStackTrace();
				}
			}

		}
		return ""; // code will never reach here until file does not exists
	}

}
