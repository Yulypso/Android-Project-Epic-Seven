package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Assets implements Parcelable {
    private String icon;
    private String image;
    private String thumbnail;

    public Assets(String icon, String image, String thumbnail) {
        this.icon = icon;
        this.image = image;
        this.thumbnail = thumbnail;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    private Assets(Parcel in) {
        icon = in.readString();
        image = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<Assets> CREATOR = new Creator<Assets>() {
        @Override
        public Assets createFromParcel(Parcel in) {
            return new Assets(in);
        }

        @Override
        public Assets[] newArray(int size) {
            return new Assets[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(image);
        dest.writeString(thumbnail);
    }

    public String getIcon() {
        return icon;
    }

    public String getImage() {
        return image;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
