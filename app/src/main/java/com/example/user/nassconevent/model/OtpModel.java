package com.example.user.nassconevent.model;

/**
 * Created by user on 25/5/17.
 */

public class OtpModel {
    private int success;

    public OtpModel(int success, String message, OtpModelData data) {

        this.success = success;
        this.message = message;
        this.data = data;
    }

    private String message;
    private OtpModelData data;

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

    public OtpModelData getData() {
        return data;
    }

    public void setData(OtpModelData data) {
        this.data = data;
    }

    public static class OtpModelData{
        private int otp;

        public int getOtp() {
            return otp;
        }

        public void setOtp(int otp) {
            this.otp = otp;
        }
    }
}