package com.talitaalbu.android.livrodereceitas_culinaria.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    int id;
    String shortDescription;
    String description;
    String video;
    String thumbnailURL;

    protected Step(Parcel in) {
        shortDescription = in.readString();
        description = in.readString();
        video = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(video);
        dest.writeString(thumbnailURL);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getVideo() {
        return video;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    @Override
    public String toString() {
        return getShortDescription();
    }
}
