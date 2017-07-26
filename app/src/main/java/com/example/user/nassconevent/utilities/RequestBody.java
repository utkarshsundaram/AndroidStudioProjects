package com.example.user.nassconevent.utilities;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ubuntu on 25/5/17.
 */

public class RequestBody {
    JsonObject jsonObjectRequestForLogin=new JsonObject();

    User user;
    public RequestBody(User user) throws JSONException {
        GetHashKeyAndGetTimeStamp getHashKeyAndGetTimeStamp=new GetHashKeyAndGetTimeStamp();
        this.user=user;
        jsonObjectRequestForLogin.addProperty("hash",getHashKeyAndGetTimeStamp.getHash());
        jsonObjectRequestForLogin.addProperty("timestamp",getHashKeyAndGetTimeStamp.getTimeStamp());
        jsonObjectRequestForLogin.addProperty("email",user.getEmail());
        jsonObjectRequestForLogin.addProperty("firstName",user.getFirstName());
        jsonObjectRequestForLogin.addProperty("lastName",user.getLastName());
        jsonObjectRequestForLogin.addProperty("method","2");
    }



public  JsonObject getjsonObjectRequestForLogin(){
    return jsonObjectRequestForLogin;
}


}
