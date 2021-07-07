package com.codepath.parstagram.fragments;

import android.util.Log;
import android.widget.Toast;

import com.codepath.parstagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment{

    @Override
    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        //only have current user's posts
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        // limit query to latest 20 items
        query.setLimit(20);
        // order posts by creation date (newest first)
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "issue with getting post", e);
                    Toast.makeText(getContext(), "issue with getting post", Toast.LENGTH_SHORT).show();
                    return;
                }
                //save received posts to list
                allPosts.addAll(posts);
                //notify adapter of new data
                adapter.notifyDataSetChanged();
            }
        });
    }
}
