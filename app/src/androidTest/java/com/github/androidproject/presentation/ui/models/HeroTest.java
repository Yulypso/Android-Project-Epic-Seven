package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HeroTest {

    private Hero hero = new Hero(null, null, null, null, null, null, null);

    @Test
    public void heroParcelableTest() {
        this.hero = new Hero("test", "test", 5, "test", "test", "test", "test");
        Parcel parcel = Parcel.obtain();
        this.hero.writeToParcel(parcel, this.hero.describeContents());
        parcel.setDataPosition(0);

        Hero fromParcel = Hero.CREATOR.createFromParcel(parcel);
        assertThat(fromParcel.get_id(), is("test"));
        assertThat(fromParcel.getName(), is("test"));
        assertThat(fromParcel.getRarity(), is(5));
        assertThat(fromParcel.getRole(), is("test"));
        assertThat(fromParcel.getAttribute(), is("test"));
        assertThat(fromParcel.getZodiac(), is("test"));
        assertThat(fromParcel.getModelURL(), is("test"));
    }

    @Test
    public void setName() throws Exception {
        this.hero.setName("test");
        assertEquals("test", this.hero.getName());
    }

    @Test
    public void setRarity() {
        this.hero.setRarity(1);
        Integer val = 1;
        assertEquals(val , this.hero.getRarity());
    }

    @Test
    public void setRole() {
        this.hero.setRole("test");
        assertEquals("test", this.hero.getRole());
    }

    @Test
    public void setAttribute() {
        this.hero.setAttribute("test");
        assertEquals("test", this.hero.getAttribute());
    }

    @Test
    public void setZodiac() {
        this.hero.setZodiac("test");
        assertEquals("test", this.hero.getZodiac());
    }

    @Test
    public void setModelURL() {
        this.hero.setModelURL("test");
        assertEquals("test", this.hero.getModelURL());
    }

    @Test
    public void set_id() {
        this.hero.set_id("test");
        assertEquals("test", this.hero.get_id());
    }


    @Test
    public void getModelURL() {
        this.hero.setModelURL("test");
        assertEquals("test", this.hero.getModelURL());
    }

    @Test
    public void get_id() {
        this.hero.set_id("test");
        assertEquals("test", this.hero.get_id());
    }


    @Test
    public void getName() {
        this.hero.setName("test");
        assertEquals("test", this.hero.getName());
    }

    @Test
    public void getRarity() {
        this.hero.setRarity(1);
        Integer val = 1;
        assertEquals(val , this.hero.getRarity());
    }

    @Test
    public void getRole() {
        this.hero.setRole("test");
        assertEquals("test", this.hero.getRole());
    }

    @Test
    public void getAttribute() {
        this.hero.setAttribute("test");
        assertEquals("test", hero.getAttribute());
    }

    @Test
    public void getZodiac() {
        this.hero.setZodiac("test");
        assertEquals("test", this.hero.getZodiac());
    }
}