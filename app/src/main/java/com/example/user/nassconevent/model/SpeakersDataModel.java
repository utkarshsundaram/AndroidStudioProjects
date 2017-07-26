package com.example.user.nassconevent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 29/5/17.
 */

public class SpeakersDataModel implements Parcelable {

    public String name;
    public String post;
    public String description;
    public String pic;
    public String url;
    private int speakerID;

    protected SpeakersDataModel(Parcel in) {
        name = in.readString();
        post = in.readString();
        description = in.readString();
        pic = in.readString();
        url = in.readString();
        speakerID = in.readInt();
    }

    public static final Creator<SpeakersDataModel> CREATOR = new Creator<SpeakersDataModel>() //It creates an object of the whole class and send it to the activity
    {
        @Override
        public SpeakersDataModel createFromParcel(Parcel in) {
            return new SpeakersDataModel(in);
        }

        @Override
        public SpeakersDataModel[] newArray(int size) {
            return new SpeakersDataModel[size];
        }
    };

    public int getSpeakerID() {
        return speakerID;
    }

    public void setSpeakerID(int speakerID) {
        this.speakerID = speakerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(post);
        dest.writeString(description);
        dest.writeString(pic);
        dest.writeString(url);
        dest.writeInt(speakerID);
    }
}
