package com.example.user.nassconevent.interfaces;

/**
 * Created by user on 24/5/17.
 */

public interface ApiClient {

    public static String baseUrl="http://test.kelltontech.net/eventengine/";
    String otpURL=baseUrl+"user/getcode";
    String loginUrl=baseUrl+"user/reg";

}
