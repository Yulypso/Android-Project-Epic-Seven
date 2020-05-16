package com.github.androidproject.presentation.ui.models;

import java.util.List;

public class RestEpicSevenResponse {

    private List<Hero> results;

    public RestEpicSevenResponse(List<Hero> results) {
        this.results = results;
    }

    public void setResults(List<Hero> results) {
        this.results = results;
    }

    public List<Hero> getResults() {
        return results;
    }
}
