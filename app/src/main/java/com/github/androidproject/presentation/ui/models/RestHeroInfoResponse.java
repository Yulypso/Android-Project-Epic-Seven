package com.github.androidproject.presentation.ui.models;

import java.util.List;

public class RestHeroInfoResponse {

    private List<HeroInfo> results;

    public RestHeroInfoResponse(List<HeroInfo> results) {
        this.results = results;
    }

    public void setResults(List<HeroInfo> results) {
        this.results = results;
    }

    public List<HeroInfo> getResults() {
        return results;
    }
}

