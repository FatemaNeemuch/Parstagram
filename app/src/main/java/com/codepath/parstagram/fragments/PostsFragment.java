package com.codepath.parstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codepath.parstagram.Post;
import com.codepath.parstagram.PostsAdapter;
import com.codepath.parstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    //class constant
    public static final String TAG = "PostFragment";

    //instance variables
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //reference to views
        rvPosts = view.findViewById(R.id.rvPosts);

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        //set adapter
        rvPosts.setAdapter(adapter);
        //set layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(linearLayoutManager);

        //get posts
        queryPosts();
    }

    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
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