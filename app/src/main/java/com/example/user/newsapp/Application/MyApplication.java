package com.example.user.newsapp.Application;

import com.kelltontech.application.BaseApplication;
import com.kelltontech.volley.ext.RequestManager;

/**
 * Created by user on 5/6/17.
 */

public class MyApplication extends BaseApplication {
    @Override
    protected void initialize()
    {
        RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
    }
}
