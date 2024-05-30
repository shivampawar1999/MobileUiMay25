package com.mobile.ui.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.ui.model.HotelSearch;
import com.mobile.ui.model.dto.HotelRequestDTO;

@Controller
public class HotelController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public List<HotelRequestDTO> hotelList;
	// Initialize hotelSearch
	public HotelSearch hotelSearch = new HotelSearch();

	// Find the hotel with the matching ID
	public HotelRequestDTO selectedHotel;
	
	String defaultCountry = "";
	String defaultLang = "";

	/*------------------------------------------------------------------------*/

	@GetMapping("/{country}/{lang}/hotelSearch")
	public String hotelSearch(@PathVariable("country") String country, @PathVariable("lang") String lang, Model model) {

		defaultCountry = country;
		defaultLang = lang;
		model.addAttribute("hotelSearch", hotelSearch);
		return "hotelSearch";
	}

	@PostMapping("/hotelList")
	public String hotelList(@ModelAttribute HotelSearch hotelSearch, Model model) {

		this.hotelSearch = hotelSearch;
		model.addAttribute("hotelSearch", hotelSearch);
		// Make an HTTP GET request to the hotelDataList API
		String apiUrl = "http://localhost:8080/hotelDataList";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

		// Retrieve the data from the API response
		String hotelData = responseEntity.getBody();

		try {
			hotelList = objectMapper.readValue(hotelData, new TypeReference<List<HotelRequestDTO>>() {
			});
			// Now you have a list of HotelRequestDTO objects
			System.out.println(hotelList);
			model.addAttribute("hotelList", hotelList);
		} catch (JsonProcessingException e) {
			// Handle JSON parsing exception
			System.err.println("Error parsing JSON: " + e.getMessage());
		}

		return "hotelList";

	}

	/* calculate number of room as per user */
	private int calculateNumberOfRooms(int selectedTraveler) {
		return (selectedTraveler + 1) / 2; // This ensures 1 room for up to 2 travelers, 2 rooms for up to 3/4
											// travelers,
											// and so on.
	}

	/* calculate nights */
	public long calculateNightsStay(LocalDate fromDate, LocalDate toDate) {
        return ChronoUnit.DAYS.between(fromDate, toDate);
    }


	// execute after select room
	@PostMapping("/hotelDetail/{hotelId}")
	public String handleHotelSelection(@PathVariable("hotelId") long hotelId, Model model) {

		System.out.println("Selected hotel ID: " + hotelId);
		System.out.println(hotelSearch);

		for (HotelRequestDTO hotel : hotelList) {
			if (hotel.getHotelId() == hotelId) {
				selectedHotel = hotel;
				break;
			}
		}

		if (selectedHotel != null && hotelSearch != null) {

			// Calculate the number of rooms based on the number of travelers
			int numberOfRooms = calculateNumberOfRooms(hotelSearch.getSelectedTraveler());
			hotelSearch.setNumberOfRooms(numberOfRooms);
			

	        // Call the service to calculate the number of nights
	        long nightsStay = calculateNightsStay(hotelSearch.getFromDate(), hotelSearch.getToDate());
	        hotelSearch.setNightsStay(nightsStay);

			model.addAttribute("selectedHotel", selectedHotel);
			model.addAttribute("hotelSearch", hotelSearch);
		}

		return "hotelDetails"; // Return the name of the view to display hotel details
	}

	/* Your Itinerary controller */
	@PostMapping("/itinerary")
	public String yourItinerary(Model model) {

		model.addAttribute("selectedHotel", selectedHotel);
		model.addAttribute("hotelSearch", hotelSearch);

		return "itinerary";
	}

}
