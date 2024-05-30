package com.mobile.ui.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FragmentService {

	public boolean fragmentExists(String fragmentPath) {
		ClassPathResource resource = new ClassPathResource("templates/" + fragmentPath + ".html");
		return resource.exists() && resource.isReadable();
	}

	public boolean footerExistsForDevice(String deviceType) {
		return fragmentExists("fragments/footer-" + deviceType);
	}

	public boolean defaultFooterExists() {
		return fragmentExists("fragments/footer");
	}
}
