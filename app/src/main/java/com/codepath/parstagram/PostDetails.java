package com.codepath.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.codepath.parstagram.fragments.PostsFragment;
import com.parse.ParseException;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.Date;

public class PostDetails extends AppCompatActivity {

    //instance variables
    TextView tvCaption;
    TextView tvCreatedAt;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        //reference to views
        tvCaption = findViewById(R.id.tvCaption);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        // unwrap the post passed in via intent, using its simple name as a key
        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        //set caption
        tvCaption.setText(post.getDescription());
        //get created at time
        Date createdAt = post.getCreatedAt();
        //change to how long ago it was posted
        String timeAgo = null;
        try {
            timeAgo = Utils.getRelativeTimeAgo(createdAt);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        //set created at
        tvCreatedAt.setText(timeAgo);
    }
}