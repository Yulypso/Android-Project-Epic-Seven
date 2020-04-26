package com.github.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Hero implements Parcelable {
    private String _id;
    private String name;
    private Integer rarity;
    private String role;
    private String attribute;
    private String zodiac;
    private String modelURL;


    protected Hero(Parcel in) {
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
