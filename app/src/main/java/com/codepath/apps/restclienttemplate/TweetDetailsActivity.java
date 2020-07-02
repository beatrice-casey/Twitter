package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

public class TweetDetailsActivity extends AppCompatActivity {

    //the tweet to display
    Tweet tweet;
    TwitterClient client;


    Context context;
    TweetsAdapter adapter;

    //view objects
    TextView tvUsername;
    TextView tvBody;
    TextView tvTimestamp;
    ImageView ivProfileImage;
    ImageView ivEmbeddedMedia;
    TextView tvScreenName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
        tvUsername = findViewById(R.id.tvScreenName);
        tvBody = findViewById(R.id.tvBody);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        ivEmbeddedMedia = findViewById(R.id.ivEmbeddedMedia);
        tvScreenName = findViewById(R.id.tvName);

        //unwrap tweet passed via intent
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        context = this;
        
        int radius = 15;
        int margin = 10;
        tvUsername.setText("@" + tweet.user.screenName);
        tvScreenName.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvTimestamp.setText(tweet.timestamp);
        Glide.with(context).load(tweet.user.profileImageURL).transform(new RoundedCornersTransformation(radius, margin))
               .into(ivProfileImage);
        Glide.with(context).load(tweet.embeddedMedia).transform(new RoundedCornersTransformation(radius, margin))
                .override(Target.SIZE_ORIGINAL).into(ivEmbeddedMedia);


    }
}