package com.github.androidproject;

import java.util.List;

public class Hero {
    private String _id;
    private String name;
    private Integer rarity;
    private String role;
    private String attribute;
    private String zodiac;
    private String modelURL;


    public void setModelURL(String modelURL) {
        this.modelURL = modelURL;
    }

    public String getModelURL() {
        return modelURL;
    }

    public String get_id() {
        return _id;
    }
    public String getName() {
        return name;
    }

    public Integer getRarity() {
        return rarity;
    }

    public String getRole() {
        return role;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getZodiac() {
        return zodiac;
    }
}
