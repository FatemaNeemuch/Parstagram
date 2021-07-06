package com.codepath.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Register your parse models
        ParseObject.registerSubclass(Post.class);
        //set applicationID, and server based on the values in the back4app settings
        //client key is not needed unless explicitly configured
        //any network interceptors must be added with the Configuration builder given in syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("JzJvD7X9Vt2iZaM8KCHS0hGG2FOBUHsp0RVDzx1M")
                .clientKey("erqgsqqj7m8sgtWNepcFcz6EhdPQmJiJJDODJRSP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
