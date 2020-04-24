package com.github.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Assets implements Parcelable {
    private String icon;
    private String image;
    private String thumbnail;

    protected Assets(Parcel in) {
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
