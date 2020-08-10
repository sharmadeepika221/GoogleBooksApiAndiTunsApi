package com.IDEXX.animana.assessment.controller;

import com.IDEXX.animana.assessment.exception.ResourceNotFoundException;
import com.IDEXX.animana.assessment.model.SearchResponse;
import com.IDEXX.animana.assessment.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/search")
@Api(tags = { "search" })
public class SearchController extends AbstractRestHandler{

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    private SearchService searchService;

    /**
     * This method is for retrieving results of 5 books and 5 albums
     * @param searchString;
     * @return ResponseEntityList<SearchResponse>
     */
    @GetMapping("/retrieveResult/{searchString}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Gets Books and albums related to the search string.", notes = "Gives 5 books nd 5 albums that are related to input field sorted by title alphabetically")
    public @ResponseBody
    ResponseEntity<List<SearchResponse>> getBooksAndAlbumsResults(@PathVariable String searchString) {

        List<SearchResponse> results = searchService.retrieveBookaAndAlbumResults(searchString);
        if (results == null) {
            logger.error("No Books and albums Results available");
            throw new ResourceNotFoundException("No record found");
        }
        return new ResponseEntity<List<SearchResponse>>(results, HttpStatus.OK);
    }
}
