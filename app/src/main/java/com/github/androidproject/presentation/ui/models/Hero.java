package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable {

    private String _id;
    private String name;
    private Integer rarity;
    private String role;
    private String attribute;
    private String zodiac;
    private String modelURL;

    public Hero(String _id, String name, Integer rarity, String role, String attribute, String zodiac, String modelURL) {
        this._id = _id;
        this.name = name;
        this.rarity = rarity;
        this.role = role;
        this.attribute = attribute;
        this.zodiac = zodiac;
        this.modelURL = modelURL;
    }

    private Hero(Parcel in) {
        _id = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            rarity = null;
        } else {
            rarity = in.readInt();
        }
        role = in.readString();
        attribute = in.readString();
        zodiac = in.readString();
        modelURL = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
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
        if (rarity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rarity);
        }
        dest.writeString(role);
        dest.writeString(attribute);
        dest.writeString(zodiac);
        dest.writeString(modelURL);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public void setModelURL(String modelURL) {
        this.modelURL = modelURL;
    }

    public String getModelURL() {
        return modelURL;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
