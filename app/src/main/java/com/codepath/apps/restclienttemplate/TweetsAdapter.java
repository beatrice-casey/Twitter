package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    //Pass in the context and list of tweets (through constructor)
    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //for each row, inflate the layout for a tweet
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the item tweet layout.
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        //wrap view inside the view holder we defined.
        return new ViewHolder(view);
    }

    //Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data at position
        Tweet tweet = tweets.get(position);
        //bind the tweet with the view holder passed in.
        holder.bind(tweet);
    }


    @Override
    public int getItemCount() {
        return tweets.size();
    }


    /* when the user swipes down to refresh, an api call is immediately fired to get new data,
        when the new data comes back, we clear the data in the adapter/rv and then add all the
        new data we just got from the API back to the adapter.
     */
    // Clean all elements of the recycler view after making the API call to get new tweets
    public void clear() {
        //modifying the existing reference
        tweets.clear();
        notifyDataSetChanged();
    }

    // add all the new tweets to the recycler view
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }



    //Define a view holder

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTimestamp;
        ImageView ivEmbeddedMedia;

        //represents one row in the recycler view (tweet)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            ivEmbeddedMedia = itemView.findViewById(R.id.ivEmbeddedMedia);
            itemView.setOnClickListener(this);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.profileImageURL).into(ivProfileImage);
            tvTimestamp.setText(tweet.timestamp);
            Glide.with(context).load(tweet.embeddedMedia).into(ivEmbeddedMedia);
        }

        @Override
        public void onClick(View view) {
            //getting adapter position
            int position = getAdapterPosition();
            //make sure position is valid (it exists in view)
            if (position != RecyclerView.NO_POSITION) {
                //get the movie at that position
                Tweet tweet = tweets.get(position);
                //make an intent to display MovieDetailsActivity
                Intent intent = new Intent(context, TweetDetailsActivity.class);
                //serialize the movie using parceler, use short name as key
                intent.putExtra("tweet", Parcels.wrap(tweet));
                //show the activity
                context.startActivity(intent);
            }
        }
    }




}
