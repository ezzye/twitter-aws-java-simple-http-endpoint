package com.serverless;
//
//import com.serverless.model.Request;
//import com.serverless.model.TwitterCredentials;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import twitter4j.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TwitterFeedTest {
//
//    TwitterFeed twitterFeed;
//    Twitter twitter;
//    Request request;
//
//
//    @Before
//    public void setUp() throws Exception {
//        twitterFeed = new TwitterFeed();
//        twitter = twitterFeed.getTwitter();
//    }
//
//    @Test
//    public void twitterCredentialReturnsTwitterCredentialsTest() {
//        TwitterCredentials expected = new TwitterCredentials();
//        expected.setAccessSecret("u5dCrttXv4gxkaxWnrcxygROgAUyKe1s67xlFJEd4w1T0");
//        expected.setAccessToken("2721231638-mS3a2aI4KsUQNZJ44BqjXlkVMtFrxs21rK9VlNZ");
//        expected.setConsumerKey("kjHxRqTEB6RYOjjFBU6hrEnOn");
//        expected.setConsumerSecret("TIqPYuqgm9k3RbaxSSxVAnT2BnqTOlEhFicZqCHKDo3TI8bYJI");
//
//        TwitterCredentials actual = TwitterFeed.readCredentials();
//
//        assertThat(actual,equalTo(expected));
//
//    }
//
//    @Test
//    public void getTwitterReturnsATwitterClientTest() {
//        Twitter twitter = twitterFeed.getTwitter();
//        assertThat(twitter,instanceOf(Twitter.class));
//    }
//
//    @Test
//    public void readFeedReturnsSizeOfTimeLineTest() throws TwitterException {
//        request = new Request();
//        request.setName("FreeHackney");
//        String expected = twitterFeed.readFeed(request,twitter).get(0).getUser().getName();
//        assertThat(expected,is("FreeHackney"));
//    }
//
//}