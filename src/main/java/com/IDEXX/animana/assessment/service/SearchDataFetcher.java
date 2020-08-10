package com.IDEXX.animana.assessment.service;

import com.IDEXX.animana.assessment.model.SearchResponse;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchDataFetcher implements DataFetcher<List<SearchResponse>> {
    @Autowired
    private SearchService searchService;

    @Override
    public List<SearchResponse> get(DataFetchingEnvironment dataFetchingEnvironment) {
        String title = dataFetchingEnvironment.getArgument("title");
        List<SearchResponse> searchList;
        //log.info("Fetching all google books with title {}", title);
        searchList = searchService.retrieveBookaAndAlbumResults(title);
        return searchList;
    }
}
