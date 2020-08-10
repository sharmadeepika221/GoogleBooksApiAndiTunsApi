package com.IDEXX.animana.assessment.service;

import com.IDEXX.animana.assessment.model.SearchResponse;

import java.util.List;

public interface SearchService {
    List<SearchResponse> retrieveBookaAndAlbumResults(String searchString);
}
