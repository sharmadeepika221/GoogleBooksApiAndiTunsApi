package com.IDEXX.animana.assessment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @InjectMocks
    SearchServiceImpl searchService;

    @Test
    public void testGetBooks() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(new URI("https://www.googleapis.com/books/v1/volumes?maxResults=1&q=abc"), String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(true, result.getBody().contains("items"));
    }

    @Test
    public void testGetAlbums() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(new URI("https://itunes.apple.com/search?term=jack+johnson&entity=album&limit=1"), String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(true, result.getBody().contains("results"));
    }


}

