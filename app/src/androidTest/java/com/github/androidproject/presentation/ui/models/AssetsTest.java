package com.github.androidproject.presentation.ui.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssetsTest {

    private Assets assets = new Assets(null, null, null);

    @Test
    public void setIcon() {
        this.assets.setIcon("test");
        assertEquals("test", this.assets.getIcon());
    }

    @Test
    public void setImage() {
        this.assets.setImage("test");
        assertEquals("test", this.assets.getImage());
    }

    @Test
    public void setThumbnail() {
        this.assets.setThumbnail("test");
        assertEquals("test", this.assets.getThumbnail());
    }

    @Test
    public void getIcon() {
        this.assets.setIcon("test");
        assertEquals("test", this.assets.getIcon());
    }

    @Test
    public void getImage() {
        this.assets.setImage("test");
        assertEquals("test", this.assets.getImage());
    }

    @Test
    public void getThumbnail() {
        this.assets.setThumbnail("test");
        assertEquals("test", this.assets.getThumbnail());
    }

}