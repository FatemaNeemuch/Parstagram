package com.codepath.parstagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.parstagram.Post;
import com.codepath.parstagram.ProfileAdapter;
import com.codepath.parstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment{

    //class constant
    public static final String TAG = "ProfileFragment";
    public static final int QUERY_LIMIT = 20;
    public static final int PAGE = 0;
    public static final int SPAN_COUNT = 3;

    //instance variables
    protected RecyclerView rvProfile;
    protected ProfileAdapter profileAdapter;
    protected List<Post> userPosts;
    protected TextView tvUsernameProfile;
    protected SwipeRefreshLayout swipeContainerProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //reference to views
        rvProfile = view.findViewById(R.id.rvProfile);
        tvUsernameProfile = view.findViewById(R.id.tvUsernameProfile);

        userPosts = new ArrayList<>();
        profileAdapter = new ProfileAdapter(getContext(), userPosts);
        //set adapter
        rvProfile.setAdapter(profileAdapter);
        //set layout manager
        rvProfile.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        //set username
        tvUsernameProfile.setText(ParseUser.getCurrentUser().getUsername());

        // Lookup the swipe container view
        swipeContainerProfile = view.findViewById(R.id.swipeContainerProfile);
        // Setup refresh listener which triggers new data loading
        swipeContainerProfile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(PAGE);
            }
        });
        // Configure the refreshing colors
        swipeContainerProfile.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //get posts
        queryPosts();
    }

    public void fetchTimelineAsync(int page) {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        //only have current user's posts
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        // limit query to latest 20 items
        query.setLimit(QUERY_LIMIT);
        // order posts by creation date (newest first)
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, getString(R.string.fetch_timeline_log), e);
                    return;
                }
                //clear posts
                profileAdapter.clear();
                //save received posts to list
                profileAdapter.addAll(posts);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainerProfile.setRefreshing(false);
            }
        });
    }

    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        //only have current user's posts
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        // limit query to latest 20 items
        query.setLimit(QUERY_LIMIT);
        // order posts by creation date (newest first)
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "issue with getting post", e);
                    return;
                }
                //save received posts to list
                userPosts.addAll(posts);
                //notify adapter of new data
                profileAdapter.notifyDataSetChanged();
            }
        });
    }
}
