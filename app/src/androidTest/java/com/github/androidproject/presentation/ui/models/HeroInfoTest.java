package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HeroInfoTest {

    private Assets assets = new Assets(null, null, null);
    private Relations relations = null;
    private List<Relations> relationsList = null;
    private Relationships relationships = null;
    private List<Relationships> relationshipsList = null;
    private HeroInfo heroInfo = new HeroInfo(null, null, null, null, null, null, null, null);

    @Test
    public void herInfoParcelableTest() {
        this.assets = new Assets("test", "test", "test");
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);
        this.relationshipsList = new ArrayList<>();
        this.relationshipsList.add(this.relationships);
        this.heroInfo = new HeroInfo("test", "test", this.assets, "test", "test", "test", "test", this.relationshipsList);

        Parcel parcel = Parcel.obtain();
        this.heroInfo.writeToParcel(parcel, this.heroInfo.describeContents());
        parcel.setDataPosition(0);

        HeroInfo fromParcel = HeroInfo.CREATOR.createFromParcel(parcel);
        assertThat(fromParcel.get_id(), is("test"));
        assertThat(fromParcel.getId(), is("test"));
        assertThat(fromParcel.getAssets().getIcon(), is(this.assets.getIcon()));
        assertThat(fromParcel.getName(), is("test"));
        assertThat(fromParcel.getDescription(), is("test"));
        assertThat(fromParcel.getStory(), is("test"));
        assertThat(fromParcel.getGet_line(), is("test"));
        assertThat(fromParcel.getRelationships().get(0).getRelations().get(0).getId(), is(this.relationshipsList.get(0).getRelations().get(0).getId()));
    }

    @Test
    public void get_id() {
        this.heroInfo.set_id("test");
        assertEquals("test", this.heroInfo.get_id());
    }

    @Test
    public void set_id() {
        this.heroInfo.set_id("test");
        assertEquals("test", this.heroInfo.get_id());
    }

    @Test
    public void getId() {
        this.heroInfo.setId("test");
        assertEquals("test", this.heroInfo.getId());
    }

    @Test
    public void setId() {
        this.heroInfo.setId("test");
        assertEquals("test", this.heroInfo.getId());
    }

    @Test
    public void getAssets() {
        this.assets = new Assets("test", "test", "test");
        this.heroInfo.setAssets(assets);
        assertEquals(assets, this.heroInfo.getAssets());
    }

    @Test
    public void setAssets() {
        this.assets = new Assets("test", "test", "test");
        this.heroInfo.setAssets(assets);
        assertEquals(assets, this.heroInfo.getAssets());
    }

    @Test
    public void getName() {
        this.heroInfo.setName("test");
        assertEquals("test", this.heroInfo.getName());
    }

    @Test
    public void setName() {
        this.heroInfo.setName("test");
        assertEquals("test", this.heroInfo.getName());
    }

    @Test
    public void getDescription() {
        this.heroInfo.setDescription("test");
        assertEquals("test", this.heroInfo.getDescription());
    }

    @Test
    public void setDescription() {
        this.heroInfo.setDescription("test");
        assertEquals("test", this.heroInfo.getDescription());
    }

    @Test
    public void getStory() {
        this.heroInfo.setStory("test");
        assertEquals("test", this.heroInfo.getStory());
    }

    @Test
    public void setStory() {
        this.heroInfo.setStory("test");
        assertEquals("test", this.heroInfo.getStory());
    }

    @Test
    public void getGet_line() {
        this.heroInfo.setGet_line("test");
        assertEquals("test", this.heroInfo.getGet_line());
    }

    @Test
    public void setGet_line() {
        this.heroInfo.setGet_line("test");
        assertEquals("test", this.heroInfo.getGet_line());
    }

    @Test
    public void getRelationships() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);
        this.relationshipsList = new ArrayList<>();
        this.relationshipsList.add(this.relationships);

        this.heroInfo.setRelationships(this.relationshipsList);
        assertEquals(this.relationshipsList, this.heroInfo.getRelationships());
    }

    @Test
    public void setRelationships() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);
        this.relationshipsList = new ArrayList<>();
        this.relationshipsList.add(this.relationships);

        this.heroInfo.setRelationships(this.relationshipsList);
        assertEquals(this.relationshipsList, this.heroInfo.getRelationships());
    }
}