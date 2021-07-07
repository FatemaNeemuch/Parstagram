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
        //get user
        query.include(Post.KEY_USER);
        //only have current user's posts
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        //limit number of posts gotten
        query.setLimit(20);
        //show the newest posts at the top of the feed
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "issue with getting post", e);
                    Toast.makeText(getContext(), "issue with getting post", Toast.LENGTH_SHORT).show();
                    return;
                }
//                //loop through all the posts
//                for (Post post : posts){
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//                //present posts on timeline with newest on top
//                for (int i = posts.size() - 1; i >= 0; i--){
//                    allPosts.add(posts.get(i));
//                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
