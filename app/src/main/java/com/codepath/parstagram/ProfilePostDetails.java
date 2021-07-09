package com.codepath.parstagram;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class ProfilePostDetails extends AppCompatActivity {

    //instance variables
    TextView tvUsernameDetails;
    TextView tvDescriptionDetails;
    ImageView ivImageDetails;
    TextView tvCreatedAtDetails;
    Post post;
//    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_post_details);

        //reference to views
        tvUsernameDetails = findViewById(R.id.tvUsernameDetails);
        tvDescriptionDetails = findViewById(R.id.tvDescriptionDetails);
        ivImageDetails = findViewById(R.id.ivImageDetails);
        tvCreatedAtDetails = findViewById(R.id.tvCreatedAtDetails);

        // unwrap the post passed in via intent, using its simple name as a key
        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

//        utils = new Utils();

        //set username
        tvUsernameDetails.setText(post.getUser().getUsername());
        //set caption
        tvDescriptionDetails.setText(post.getDescription());
        //get created at time
        Date createdAt = post.getCreatedAt();
        //change to how long ago it was posted
        String timeAgo = Utils.calculateTimeAgo(createdAt);
        //set created at
        tvCreatedAtDetails.setText(timeAgo);
        //check if image exists for post
        ParseFile image = post.getImage();
        //check if image exists for post
        if (image != null){
            //set image for post if applicable
            Glide.with(this).load(image.getUrl()).into(ivImageDetails);
        }
    }
}
