package com.IDEXX.animana.assessment.controller;

import com.IDEXX.animana.assessment.model.SearchResponse;
import com.IDEXX.animana.assessment.service.SearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SearchControllerTest {

    @MockBean
    private SearchService searchService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /retrieveResult - Success")
    void testSearchResult_success() throws Exception {
        List<SearchResponse> results = new ArrayList<>();

        SearchResponse result1 = new SearchResponse("title1", "author1", "Book");
        SearchResponse result2 = new SearchResponse("title2", "author2", "Album");
        results.add(result1);
        results.add(result2);

        doReturn(results).when(searchService).retrieveBookaAndAlbumResults("title");
        mockMvc.perform(get("/api/v1/search//retrieveResult/{searchString}", "title"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(results.get(0).getTitle())))
                .andExpect(jsonPath("$[0].authors", equalTo(results.get(0).getAuthors())))
                .andExpect(jsonPath("$[0].itemName", equalTo(results.get(0).getItemName())))
                .andExpect(jsonPath("$[1].title", equalTo(results.get(1).getTitle())))
                .andExpect(jsonPath("$[1].authors", equalTo(results.get(1).getAuthors())))
                .andExpect(jsonPath("$[1].itemName", equalTo(results.get(1).getItemName())));
    }

}