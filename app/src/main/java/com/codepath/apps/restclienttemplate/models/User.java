package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * this class holds all the attributes that a user has, i.e profile picture, username and screenname.
 * It takes a Json object and converts that data to a user object.
 */

@Parcel
public class User {

    public String name;
    public String screenName;
    public String profileImageURL;

    //empty constructor needed by Parceler library
    public User(){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageURL = jsonObject.getString("profile_image_url_https");
        return user;

    }
}
