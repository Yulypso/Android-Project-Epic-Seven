package com.github.androidproject;

import java.util.List;

public class HeroInfo {

    private String _id;
    private Assets assets;
    private String name;

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
