package com.example.user.nassconevent.model;

/**
 * Created by ubuntu on 25/5/17.
 */

public class LoginModel {
    private  int success;
    private dataModelData data[];
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public dataModelData[] getData() {
        return data;
    }

    public void setData(dataModelData[] data) {
        this.data = data;
    }

    private static class dataModelData {
        private chatModelData chat;
        private int id;
        private int tokenSaved;
        private  int privacy;

        public chatModelData getChat() {
            return chat;
        }

        public void setChat(chatModelData chat) {
            this.chat = chat;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTokenSaved() {
            return tokenSaved;
        }

        public void setTokenSaved(int tokenSaved) {
            this.tokenSaved = tokenSaved;
        }

        public int getPrivacy() {
            return privacy;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }
    }
    private static class chatModelData{
        private int id;
        private String login;
        private int password;
        private String message;
        private int success;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getPassword() {
            return password;
        }

        public void setPassword(int password) {
            this.password = password;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }


    }
}
