package com.github.androidproject;

import java.util.List;

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

    String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    Assets getAssets() {
        return assets;
    }
}
