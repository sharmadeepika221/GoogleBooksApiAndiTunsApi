package com.IDEXX.animana.assessment.service;

import com.IDEXX.animana.assessment.model.SearchResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchService {
    List<SearchResponse> retrieveBookaAndAlbumResults(String searchString);
    CompletableFuture<List<SearchResponse>> getBooks(String name);
    CompletableFuture<List<SearchResponse>> getAlbums(String name);
}
