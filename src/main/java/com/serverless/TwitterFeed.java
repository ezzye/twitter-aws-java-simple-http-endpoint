package com.serverless;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.Request;
import com.serverless.model.TwitterCredentials;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TwitterFeed {
    public static void main(String[] args) {
        TwitterFeed twitterFeed = new TwitterFeed();
        Request request = new Request();
        request.setName(args[0]);
        twitterFeed.readFeed(request,twitterFeed.getTwitter());
    }

    private static void accept(Status status) {
        String json = TwitterObjectFactory.getRawJSON(status);
        JSONObject jsonObject = null; // Convert text to object

        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(jsonObject.toString(4)); // Print it with specified indentation
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    List<Status> readFeed(Request request) {
        return readFeed(request,new TwitterFeed().getTwitter());
    }

    List<Status> readFeed(Request request, Twitter twitter) {
        try {
            int page = 1;
            int count = 2;
            Paging paging = new Paging(page, count);
            List<Status> list = twitter.getUserTimeline(request.getName(), paging);
            list.stream().forEach(TwitterFeed::accept);
            return list;
        } catch (TwitterException ex) {
            throw new RuntimeException(ex);
        }
    }

    Twitter getTwitter() {

        TwitterCredentials creds = readCredentials();
        if(creds.getConsumerKey() == null) {
            throw new RuntimeException("Incorrect Twitter client configuration: Consumer key is null");
        }
        if (creds.getConsumerSecret() == null) {
            throw new RuntimeException("Incorrect Twitter client configuration: Consumer secret is null");
        }
        if (creds.getAccessSecret() == null) {
            throw new RuntimeException("Incorrect Twitter client configuration: Access secret is null");
        }
        if (creds.getAccessToken() == null) {
            throw new RuntimeException("Incorrect Twitter client configuration: Access token is null");
        }
        ConfigurationBuilder twitterConfig = new ConfigurationBuilder();
        twitterConfig.setOAuthConsumerKey(creds.getConsumerKey());
        twitterConfig.setOAuthConsumerSecret(creds.getConsumerSecret());
        twitterConfig.setOAuthAccessToken(creds.getAccessToken());
        twitterConfig.setOAuthAccessTokenSecret(creds.getAccessSecret());
        twitterConfig.setJSONStoreEnabled(true);
        return new TwitterFactory(twitterConfig.build()).getInstance();
    }

    static TwitterCredentials readCredentials() {

        ClassLoader classLoader = new TwitterFeed().getClass().getClassLoader();
        try(InputStream is = classLoader.getResourceAsStream("twitter.json")) {
            if (is == null) {
                throw new RuntimeException("Incorrect Twitter client configuration: Configuration file not found");
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, TwitterCredentials.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
