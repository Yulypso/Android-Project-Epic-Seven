package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;

import com.google.gson.internal.$Gson$Preconditions;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RelationsTest {

    private Relations relations = new Relations(null, null, null, null, null);

    @Test
    public void heroParcelableTest() {
        this.relations = new Relations("test", 0, "test", "test", "test");
        Parcel parcel = Parcel.obtain();
        this.relations.writeToParcel(parcel, this.relations.describeContents());
        parcel.setDataPosition(0);

        Relations fromParcel = Relations.CREATOR.createFromParcel(parcel);
        assertThat(fromParcel.getId(), is("test"));
        assertThat(fromParcel.getSlot(), is(0));
        assertThat(fromParcel.getDescription(), is("test"));
        assertThat(fromParcel.getRelation(), is("test"));
        assertThat(fromParcel.getRelation_id(), is("test"));
    }

    @Test
    public void getId() {
        this.relations.setId("test");
        assertEquals("test", this.relations.getId());
    }

    @Test
    public void setId() {
        this.relations.setId("test");
        assertEquals("test", this.relations.getId());
    }

    @Test
    public void getSlot() {
        this.relations.setSlot(0);
        Integer val = 0;
        assertEquals(val, this.relations.getSlot());
    }

    @Test
    public void setSlot() {
        this.relations.setSlot(0);
        Integer val = 0;
        assertEquals(val, this.relations.getSlot());
    }

    @Test
    public void getDescription() {
        this.relations.setDescription("test");
        assertEquals("test", this.relations.getDescription());
    }

    @Test
    public void setDescription() {
        this.relations.setDescription("test");
        assertEquals("test", this.relations.getDescription());
    }

    @Test
    public void getRelation() {
        this.relations.setRelation("test");
        assertEquals("test", this.relations.getRelation());
    }

    @Test
    public void setRelation() {
        this.relations.setRelation("test");
        assertEquals("test", this.relations.getRelation());
    }

    @Test
    public void getRelation_id() {
        this.relations.setRelation_id("test");
        assertEquals("test", this.relations.getRelation_id());
    }

    @Test
    public void setRelation_id() {
        this.relations.setRelation_id("test");
        assertEquals("test", this.relations.getRelation_id());
    }
}