package com.github.androidproject.models;

import com.github.androidproject.models.Assets;

public class HeroInfo {

    private String _id;
    private Assets assets;
    private String name;

    private String description;
    private String story;
    private String get_line;

    public String getDescription() {
        return description;
    }

    public String getStory() {
        return story;
    }

    public String getGet_line() {
        return get_line;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Assets getAssets() {
        return assets;
    }
}
