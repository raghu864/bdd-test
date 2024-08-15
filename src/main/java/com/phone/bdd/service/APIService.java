package com.phone.bdd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class APIService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "https://api.restful-api.dev/objects";

    public List<Map<String, Object>> getAllPhones() {
        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);
        return response.getBody();
    }

    public Map<String, Object> getPhoneWithLowestCost() {
        List<Map<String, Object>> phones = getAllPhones();

        Optional<Map<String, Object>> phoneWithLowestCost = phones.stream()
                .filter(phone -> {
                    Map<String, Object> data = (Map<String, Object>) phone.get("data");
                    return data != null && data.get("price") instanceof Number;
                })
                .min((p1, p2) -> {
                    Double price1 = ((Number) ((Map<String, Object>) p1.get("data")).get("price")).doubleValue();
                    Double price2 = ((Number) ((Map<String, Object>) p2.get("data")).get("price")).doubleValue();
                    return Double.compare(price1, price2);
                });

        return phoneWithLowestCost.orElse(null);
    }

    public void postPhone(Map<String, Object> phoneData) {
        restTemplate.postForEntity(baseUrl, phoneData, String.class);
    }

    public void deletePhone(String id) {
        restTemplate.delete(baseUrl + "/" + id);
    }

    public void updatePhone(String id, Map<String, Object> phoneData) {
        restTemplate.put(baseUrl + "/" + id, phoneData);
    }
}
