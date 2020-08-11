package com.IDEXX.animana.assessment.service;

import com.IDEXX.animana.assessment.model.SearchResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Service class to fetch data from Google Books Api and iTunes API.
 */
@Service
public class SearchServiceImpl implements SearchService{

    private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private MeterRegistry registry;

    @Value("${rest.api.books.url}")
    private String googleBooksApiUrl;
    @Value("${rest.api.albums.url}")
    private String iTunesAlbumsApiUrl;
    @Value("${rest.api.books.limit}")
    private String booksLimit;
    @Value("${rest.api.albums.limit}")
    private String albumsLimit;

    /**
     * @param searchString
     * @return
     */
    public List<SearchResponse> retrieveBookaAndAlbumResults(String searchString) {
        CompletableFuture<List<SearchResponse>> booksList = getBooks(searchString);
        CompletableFuture<List<SearchResponse>> albumsList = getAlbums(searchString);
        List<SearchResponse> searchResults = null;
        try {
            searchResults = new ArrayList<SearchResponse>(booksList.get());
            searchResults.addAll(albumsList.get());
        } catch (Throwable e) {
            log.error("There is some issue in getting the result from upstream", e);
        }
        Collections.sort(searchResults, Comparator.comparing(SearchResponse::getTitle));
        return searchResults;

    }

    /**
     * This method calls to Google Books API and gets the 5 books related to input string.
     * @param name
     * @return
     */
    @Async
    public CompletableFuture<List<SearchResponse>> getBooks(String name) {
        name = name.replaceAll(" ", "%20");
        String url = googleBooksApiUrl + name + "&maxResults=" +booksLimit;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        // Fail if primitive values are null
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        ArrayList<SearchResponse> books = new ArrayList<>();
        try {
            JsonNode jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode items = jsonNode.get("items");
            if (items != null && items.isArray()) {
                for (JsonNode item : items) {
                    books.add(processItems(item));
                }
            }

        } catch (IOException e) {
            log.error("Exception happened: {}", e.getMessage());
        }

        return CompletableFuture.completedFuture(books);
    }

    private SearchResponse processItems(JsonNode items) {
        StringBuilder authorsSB = new StringBuilder();

        JsonNode volumeInfo = items.get("volumeInfo");
        String title = volumeInfo.get("title").asText();

        JsonNode authors = volumeInfo.get("authors");
        if (authors != null) {
            if (authors.isArray()) {
                for (JsonNode author : authors) {
                    authorsSB.append(author.asText());
                }
            }
        }
        SearchResponse book = new SearchResponse(title, authorsSB.toString(), "Book");
        return book;
    }

    /**
     * This method calls to iTunes API and gets the 5 albums related to input string.
     * @param name
     * @return
     */
    @Async
    public CompletableFuture<List<SearchResponse>> getAlbums(String name) {

        name = name.replaceAll(" ", "%20");
        String url = iTunesAlbumsApiUrl + name + "&limit=" + albumsLimit;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        // Fail if primitive values are null
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        ArrayList<SearchResponse> albums = new ArrayList<>();
        try {
            JsonNode jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode results = jsonNode.get("results");
            if (results != null && results.isArray()) {
                for (JsonNode result : results) {
                    String title = result.get("collectionName").asText();
                    String authors = result.get("artistName").asText();
                    albums.add(new SearchResponse(title, authors, "Album"));
                }
            }

        } catch (IOException e) {
            log.error("Exception happened: {}", e.getMessage());
        }

        return CompletableFuture.completedFuture(albums);
    }
}
