package com.codepath.parstagram;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    //class constants
    public static final String TAG = "PostsAdapter";
    //instance variables
    Context context;
    List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        //get post
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //instance variables
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedAtPost;
        private ImageView ivLiked;
        private TextView tvLikes;
        private boolean isDoubleClicked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //reference to views
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAtPost = itemView.findViewById(R.id.tvCreatedAtPost);
            ivLiked = itemView.findViewById(R.id.ivLiked);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            //set username
            tvUsername.setText(post.getUser().getUsername());
            //set caption
            tvDescription.setText(post.getDescription());
            //set likes status
            tvLikes.setText(post.getLikes() + "");
            //set liked status
            if (post.getLiked()){
                Glide.with(context).load(R.drawable.ic_vector_heart).into(ivLiked);
            }else{
                Glide.with(context).load(R.drawable.ic_vector_heart_stroke).into(ivLiked);
            }
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
            tvCreatedAtPost.setText(timeAgo);
            ParseFile image = post.getImage();
            //check if image exists for post
            if (image != null){
                //set image for post if applicable
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }



            isDoubleClicked=false;

            Handler handler=new Handler();
            Runnable r=new Runnable(){
                @Override
                public void run(){
                    //Actions when Single Clicked
                    isDoubleClicked = false;
                }
            };

            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isDoubleClicked){
                        //Actions when double Clicked
                        if (post.getLiked()){
                            post.setLiked(false);
                            post.setLikes(post.getLikes() - 1);
                            //set likes status
                            tvLikes.setText(post.getLikes() + "");
                            //set liked image status
                            Glide.with(context).load(R.drawable.ic_vector_heart_stroke).into(ivLiked);
                        }else {
                            post.setLiked(true);
                            post.setLikes(post.getLikes() + 1);
                            //set likes status
                            tvLikes.setText(post.getLikes() + "");
                            //set liked image status
                            Glide.with(context).load(R.drawable.ic_vector_heart).into(ivLiked);
                        }
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null){
                                    Log.e(TAG, "error", e);
                                }
                                Log.i(TAG, "it worked");
                            }
                        });
                        isDoubleClicked=false;
                        //remove callbacks for Handlers
                        handler.removeCallbacks(r);
                    }else{
                        isDoubleClicked=true;
                        handler.postDelayed(r,500);
                    }
                }
            });
        }

        // when the user clicks on a post, show PostDetails for the selected post
        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Post post = posts.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, PostDetails.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                // show the activity
                context.startActivity(intent);
            }
        }
    }
}
