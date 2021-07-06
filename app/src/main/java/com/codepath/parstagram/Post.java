package com.codepath.parstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {

    //class constants
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER = "user";
    public static final String KEY_IMAGE = "image";

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
}
