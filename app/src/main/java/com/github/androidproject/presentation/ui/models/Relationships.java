package com.github.androidproject.presentation.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Relationships implements Parcelable {

    private List<Relations> relations;


    private Relationships(Parcel in) {
        relations = in.createTypedArrayList(Relations.CREATOR);
    }

    public static final Creator<Relationships> CREATOR = new Creator<Relationships>() {
        @Override
        public Relationships createFromParcel(Parcel in) {
            return new Relationships(in);
        }

        @Override
        public Relationships[] newArray(int size) {
            return new Relationships[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(relations);
    }

    public List<Relations> getRelations() {
        return relations;
    }

    public void setRelations(List<Relations> relations) {
        this.relations = relations;
    }
}

