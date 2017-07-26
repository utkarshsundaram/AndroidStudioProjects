package com.example.user.nassconevent.model;

import java.util.ArrayList;

public class SpeakerListModel
{
    private int success;
    private String message;
    private ArrayList<SpeakersDataModel> speakers_data;

    public SpeakerListModel(int success, String message, ArrayList<SpeakersDataModel> speakers_data)
    {
        this.success = success;
        this.message = message;
        this.speakers_data = speakers_data;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<SpeakersDataModel> getSpeakers_data() {
        return speakers_data;
    }

    public void setSpeakers_data(ArrayList<SpeakersDataModel> speakers_data) {
        this.speakers_data = speakers_data;
    }

}