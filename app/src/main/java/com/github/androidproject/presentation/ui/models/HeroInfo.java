package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HeroInfo implements Parcelable {

    private String _id;
    private String id;
    private Assets assets;
    private String name;
    private String description;
    private String story;
    private String get_line;
    private List<Relationships> relationships;

    private HeroInfo(Parcel in) {
        _id = in.readString();
        id = in.readString();
        assets = in.readParcelable(Assets.class.getClassLoader());
        name = in.readString();
        description = in.readString();
        story = in.readString();
        get_line = in.readString();
        relationships = in.createTypedArrayList(Relationships.CREATOR);
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
        dest.writeString(id);
        dest.writeParcelable(assets, flags);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(story);
        dest.writeString(get_line);
        dest.writeTypedList(relationships);
    }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getGet_line() {
        return get_line;
    }

    public void setGet_line(String get_line) {
        this.get_line = get_line;
    }

    public List<Relationships> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationships> relationships) {
        this.relationships = relationships;
    }
}
