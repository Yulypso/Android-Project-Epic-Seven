package com.github.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Relations implements Parcelable {

    private String id;
    private Integer slot;
    private String description;
    private String relation;
    private String relation_id;

    protected Relations(Parcel in) {
        id = in.readString();
        if (in.readByte() == 0) {
            slot = null;
        } else {
            slot = in.readInt();
        }
        description = in.readString();
        relation = in.readString();
        relation_id = in.readString();
    }

    public static final Creator<Relations> CREATOR = new Creator<Relations>() {
        @Override
        public Relations createFromParcel(Parcel in) {
            return new Relations(in);
        }

        @Override
        public Relations[] newArray(int size) {
            return new Relations[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        if (slot == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(slot);
        }
        dest.writeString(description);
        dest.writeString(relation);
        dest.writeString(relation_id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }
}
