package com.github.androidproject.presentation.ui.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RestEpicSevenResponseTest {

    private RestEpicSevenResponse restEpicSevenResponse;
    private Hero hero;
    private List<Hero> heroList;

    @Test
    public void getResults() {
        this.hero = new Hero("test", "test", 5, "test", "test", "test", "test");
        this.heroList = new ArrayList<>();
        this.heroList.add(this.hero);
        this.restEpicSevenResponse = new RestEpicSevenResponse(this.heroList);

        this.restEpicSevenResponse.setResults(this.heroList);
        assertEquals(this.restEpicSevenResponse.getResults().get(0).get_id(), this.restEpicSevenResponse.getResults().get(0).get_id());
    }

    @Test
    public void setResults() {
        this.hero = new Hero("test", "test", 5, "test", "test", "test", "test");
        this.heroList = new ArrayList<>();
        this.heroList.add(this.hero);
        this.restEpicSevenResponse = new RestEpicSevenResponse(this.heroList);

        this.restEpicSevenResponse.setResults(this.heroList);
        assertEquals(this.restEpicSevenResponse.getResults().get(0).get_id(), this.restEpicSevenResponse.getResults().get(0).get_id());
    }
}