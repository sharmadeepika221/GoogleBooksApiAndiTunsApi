package com.IDEXX.animana.assessment.model;

/**
 * This class holds the search response data.
 */
public class SearchResponse {
    private String title;
    private String authors;
    private String itemName;

    public SearchResponse(String title, String authors, String itemName) {
        this.title = title;
        this.authors = authors;
        this.itemName = itemName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
