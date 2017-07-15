package com.serverless;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.Request;
import org.apache.log4j.Logger;
import twitter4j.Status;

import java.util.List;




public class ServletTwitterFeedRequestHandler {

    private static final Logger LOG = Logger.getLogger(ServletTwitterFeedRequestHandler.class);

    public byte[] handleServletCall(TwitterFeed twitterFeed, ObjectMapper mapper) {

        Request request = new Request();
        request.setName("FreeHackney");
        List<Status> tweets = twitterFeed.readFeed(request);
        byte[] tweetsBytes = null;
        String tweetsStrings = "";
        try {
            tweetsBytes = mapper.writeValueAsBytes(tweets);
            tweetsStrings = mapper.writeValueAsString(tweets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        LOG.info("response: " + tweetsStrings);
        return tweetsBytes;
    }
}
