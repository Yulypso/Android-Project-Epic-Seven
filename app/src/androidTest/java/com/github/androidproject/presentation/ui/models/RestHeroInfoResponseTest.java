package com.github.androidproject.presentation.ui.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RestHeroInfoResponseTest {

    private Assets assets = new Assets(null, null, null);
    private RestHeroInfoResponse restHeroInfoResponse;
    private Relations relations = null;
    private List<Relations> relationsList = null;
    private Relationships relationships = null;
    private List<Relationships> relationshipsList = null;
    private HeroInfo heroInfo = new HeroInfo(null, null, null, null, null, null, null, null);
    private List<HeroInfo> heroInfoList;

    @Test
    public void getResults() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);
        this.relationshipsList = new ArrayList<>();
        this.relationshipsList.add(this.relationships);
        this.heroInfo = new HeroInfo("test", "test", this.assets, "test", "test", "test", "test", this.relationshipsList);
        this.heroInfoList = new ArrayList<>();
        this.heroInfoList.add(this.heroInfo);
        this.restHeroInfoResponse = new RestHeroInfoResponse(this.heroInfoList);

        this.restHeroInfoResponse.setResults(this.heroInfoList);
        assertEquals(this.restHeroInfoResponse.getResults().get(0).get_id(), this.restHeroInfoResponse.getResults().get(0).get_id());
    }

    @Test
    public void setResults() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);
        this.relationshipsList = new ArrayList<>();
        this.relationshipsList.add(this.relationships);
        this.heroInfo = new HeroInfo("test", "test", this.assets, "test", "test", "test", "test", this.relationshipsList);
        this.heroInfoList = new ArrayList<>();
        this.heroInfoList.add(this.heroInfo);
        this.restHeroInfoResponse = new RestHeroInfoResponse(this.heroInfoList);

        this.restHeroInfoResponse.setResults(this.heroInfoList);
        assertEquals(this.restHeroInfoResponse.getResults().get(0).get_id(), this.restHeroInfoResponse.getResults().get(0).get_id());
    }
}