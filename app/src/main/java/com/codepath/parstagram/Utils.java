package com.codepath.parstagram;

import android.util.Log;

import java.util.Date;

import java.util.concurrent.TimeUnit;

public class Utils{

    //there is a java library called TimeUtil- use that instead of these ints
    //make the return values res/string values
        //meaning you have to make the method not static and create an instance of Utils in postDetails
        //or create an instance of an object from the class that has the getString method here

        //the first option leads

    //get when post was posted
    public static String calculateTimeAgo(Date createdAt) {

        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            //move strings to the res/strings file
            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + "m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + "h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + "d";
            }
        } catch (Exception e) {
            Log.i("Error:", "getRelativeTimeAgo failed", e);
        }
        return "";
    }
}
/*package com.codepath.parstagram;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import java.util.concurrent.TimeUnit;

public class Utils extends AppCompatActivity {

    public Utils() {}

    //create a Util class in which the calculateTimeAgo will go
    //call the method in whatever other class needs it (static method so Util.cta)

    //get when post was posted
    public String calculateTimeAgo(Date createdAt) {

        //there is a java library called TimeUtil- use that instead of these ints
        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            //move strings to the res/strings file
            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return getString(R.string.just_now);
            } else if (diff < 2 * MINUTE_MILLIS) {
                return getString(R.string.minute_ago);
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + "m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return getString(R.string.hour_ago);
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + "h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return getString(R.string.yesterday);
            } else {
                return diff / DAY_MILLIS + "d";
            }
        } catch (Exception e) {
            Log.i("Error:", "getRelativeTimeAgo failed", e);
        }
        return "";
    }
}
*/