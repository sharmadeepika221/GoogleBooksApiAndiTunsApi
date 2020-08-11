package com.IDEXX.animana.assessment.service;

import com.IDEXX.animana.assessment.model.SearchResponse;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Data fetching class
 */
@Component
public class SearchDataFetcher implements DataFetcher<List<SearchResponse>> {
    private static final Logger log = LoggerFactory.getLogger(SearchDataFetcher.class);
    @Autowired
    private SearchService searchService;

    @Override
    public List<SearchResponse> get(DataFetchingEnvironment dataFetchingEnvironment) {
        String title = dataFetchingEnvironment.getArgument("title");
        List<SearchResponse> searchList;
        log.info("Fetching all google books and iTunes albums with title {}", title);
        searchList = searchService.retrieveBookaAndAlbumResults(title);
        return searchList;
    }
}
