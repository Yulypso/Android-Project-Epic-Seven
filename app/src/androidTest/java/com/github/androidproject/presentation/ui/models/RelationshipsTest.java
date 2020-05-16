package com.github.androidproject.presentation.ui.models;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class RelationshipsTest {

    private Relations relations = null;
    private List<Relations> relationsList = null;
    private Relationships relationships = null;

    @Test
    public void getRelations() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);

        this.relationships.setRelations(this.relationsList);
        assertEquals(this.relationsList, this.relationships.getRelations());
    }

    @Test
    public void setRelations() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        this.relationsList = new ArrayList<>();
        this.relationsList.add(this.relations);
        this.relationships = new Relationships(this.relationsList);

        this.relationships.setRelations(this.relationsList);
        assertEquals(this.relationsList, this.relationships.getRelations());
    }
}