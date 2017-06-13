package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.ApiGatewayResponse;
import com.serverless.model.Request;
import com.serverless.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;


import java.util.*;

import static org.mockito.Matchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HandlerTest {

    Handler handler;
    @Mock ObjectMapper mapper;
    @Mock Twitter twitter;
    @Mock TwitterFeed twitterFeed;
    @Mock Request request;
    @Mock Context context;
    Status status;
    Response response = new Response("{\"aFunnyString\" : \"ha ha this is not funny\" }");
    Map<String,String> headers;
    List<Status> listOfStatus = Arrays.asList(status);

    @Before
    public void setUp() throws Exception {
        handler = new Handler(twitterFeed,mapper);
        headers = new HashMap<>();

    }

    @Test
    public void handleRequest() throws Exception {
        when(request.getName()).thenReturn("FreeHackney");
        when(twitterFeed.getTwitter()).thenReturn(twitter);
        when(twitterFeed.readFeed(request)).thenReturn(listOfStatus);
        when(mapper.writeValueAsString(listOfStatus)).thenReturn("{\"aFunnyString\" : \"ha ha this is not funny\" }");

        ApiGatewayResponse actual = handler.handleRequest(request,context);
        ApiGatewayResponse expected = null;
        try {
            expected = new ApiGatewayResponse.Builder()
                    .setStatusCode(200)
                    .setObjectBody(response)
                    .setHeaders(headers)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(actual.getBody(),is(expected.getBody()));

    }

}