package com.codepath.parstagram;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.Date;

@Parcel (analyze = Post.class)
@ParseClassName("Post")
public class Post extends ParseObject {

    //class constants
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER = "user";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_CREATED_AT = "createdAt";

    // no-arg, empty constructor required for Parceler
    public Post() {}

    //get post caption
    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    //set post caption
    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    //get image
    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    //set image
    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    //get user
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    //set user
    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

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
