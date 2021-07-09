package com.codepath.parstagram;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    //class constants
    public static final String TAG = "ProfileAdapter";

    //instance variables
    Context context;
    List<Post> userPosts;

    public ProfileAdapter(Context context, List<Post> userPosts) {
        this.context = context;
        this.userPosts = userPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        //get post
        Post post = userPosts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return userPosts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        userPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        userPosts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //instance variables
        private ImageView ivImageProfile;
        private TextView tvDescriptionProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //reference to views
            ivImageProfile = itemView.findViewById(R.id.ivImageProfile);
            tvDescriptionProfile = itemView.findViewById(R.id.tvDescriptionProfile);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            //set caption
            tvDescriptionProfile.setText(post.getDescription());
            ParseFile image = post.getImage();
            //check if image exists for post
            if (image != null){
                //set image for post if applicable
                Glide.with(context).load(image.getUrl()).into(ivImageProfile);
            }
        }

        // when the user clicks on a post, show PostDetails for the selected post
        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Post post = userPosts.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, ProfilePostDetails.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                // show the activity
                context.startActivity(intent);
            }
        }
    }
}
