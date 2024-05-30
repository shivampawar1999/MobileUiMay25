package com.mobile.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationRestController {
	
	
	   // Hard coded arrayList of cities
    List<String> cities = List.of("Mumbai", "Mumbadevi", "Mumjal", "Delhi", "Bangalore", "Kolkata", "Chennai", "Hyderabad", "Ahmedabad",
            "Pune", "Surat", "Jaipur", "Lucknow", "Kanpur", "Nagpur", "Visakhapatnam", "Bhopal", "Patna", "Vadodara",
            "Ghaziabad", "Ludhiana", "Agra", "Nashik", "Faridabad", "Meerut", "Rajkot", "Varanasi", "Srinagar",
            "Aurangabad", "Dhanbad", "Amritsar", "Navi Mumbai", "");

    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam String query) {
        List<String> suggestions = new ArrayList<>();
        // Iterate over cities and find matches
        for (String city : cities) {
            if (city.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(city);
            }
        }
        return suggestions;
	}

}
