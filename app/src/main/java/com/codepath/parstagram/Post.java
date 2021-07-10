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
    public static final String KEY_LIKED = "liked";
    public static final String KEY_LIKES = "likes";

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

    //get liked
    public boolean getLiked(){
        return getBoolean(KEY_LIKED);
    }

    //set liked
    public void setLiked(boolean liked){
        put(KEY_LIKED, liked);
    }

    //get likes
    public int getLikes(){
        return getInt(KEY_LIKES);
    }

    //set likes
    public void setLikes(int likes){
        put(KEY_LIKES, likes);
    }

}
