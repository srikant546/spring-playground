package com.example.demo.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Service
public class MovieService {

    private final RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public Object getByMovieName (String movieName) {

        String url = "http://www.omdbapi.com/?apikey=d349c827&s=" + movieName;

        ParameterizedTypeReference<Map<String, Object>> responseType =
                new ParameterizedTypeReference<Map<String, Object>>() {};

       return this.restTemplate.exchange(URI.create(url), HttpMethod.GET, null, responseType).getBody().get("Search");

    }

}
