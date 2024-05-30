package com.mobile.ui.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());

			model.addAttribute("statusCode", statusCode);
			model.addAttribute("errorTitle", "Something went wrong!");
			model.addAttribute("errorMessage", getErrorMessage(HttpStatus.valueOf(statusCode)));
		}

		return "customError"; // View name for the error page
	}

	private String getErrorMessage(HttpStatus status) {
		switch (status) {
		case NOT_FOUND:
			return "The requested page was not found.";
		case FORBIDDEN:
			return "You do not have permission to access this page.";
		case INTERNAL_SERVER_ERROR:
			return "Internal server error occurred.";
		default:
			return "An unexpected error occurred.";
		}
	}

	public String getErrorPath() {
		return "/error";
	}
}
