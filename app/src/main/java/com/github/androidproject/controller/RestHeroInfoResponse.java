package com.github.androidproject.controller;

import com.github.androidproject.models.HeroInfo;

import java.util.List;

public class RestHeroInfoResponse {
    private List<HeroInfo> results;

    public List<HeroInfo> getResults() {
        return results;
    }
}

