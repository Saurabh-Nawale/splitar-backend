// src/main/java/com/yourpackage/controller/GeocodingController.java
package com.yourpackage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/geocode") // Base path for our geocoding proxy
public class GeocodingController {

    @Value("${nominatim.api.url}")
    private String nominatimApiUrl;

    private final RestTemplate restTemplate;

    public GeocodingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Endpoint for REVERSE geocoding (lat, lon -> address)
    @GetMapping("/reverse")
    public ResponseEntity<String> reverseGeocode(@RequestParam Double lat, @RequestParam Double lon) {
        String url = UriComponentsBuilder.fromHttpUrl(nominatimApiUrl + "/reverse")
                .queryParam("format", "json")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching reverse geocode data.");
        }
    }

    // Endpoint for FORWARD geocoding (address -> lat, lon)
    @GetMapping("/search")
    public ResponseEntity<String> searchGeocode(@RequestParam String q) {
        String url = UriComponentsBuilder.fromHttpUrl(nominatimApiUrl + "/search")
                .queryParam("format", "json")
                .queryParam("q", q)
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching search data.");
        }
    }
}