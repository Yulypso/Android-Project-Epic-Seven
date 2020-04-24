package com.github.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.androidproject.models.Assets;

public class HeroInfo implements Parcelable {

    private String _id;
    private Assets assets;
    private String name;

    private String description;
    private String story;
    private String get_line;

    protected HeroInfo(Parcel in) {
        _id = in.readString();
        name = in.readString();
        description = in.readString();
        story = in.readString();
        get_line = in.readString();
    }

    public static final Creator<HeroInfo> CREATOR = new Creator<HeroInfo>() {
        @Override
        public HeroInfo createFromParcel(Parcel in) {
            return new HeroInfo(in);
        }

        @Override
        public HeroInfo[] newArray(int size) {
            return new HeroInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(story);
        dest.writeString(get_line);
    }

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
