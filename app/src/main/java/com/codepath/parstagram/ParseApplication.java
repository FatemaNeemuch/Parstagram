package com.codepath.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("JzJvD7X9Vt2iZaM8KCHS0hGG2FOBUHsp0RVDzx1M")
                .clientKey("erqgsqqj7m8sgtWNepcFcz6EhdPQmJiJJDODJRSP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
