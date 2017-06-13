package com.serverless;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazon.speech.speechlet.Speechlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.ApiGatewayResponse;
import com.serverless.model.Request;
import com.serverless.model.Response;
import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import twitter4j.Status;

public class Handler implements RequestHandler<Request, ApiGatewayResponse> {

	private TwitterFeed twitterFeed;
	private ObjectMapper mapper;

	public Handler() {
		twitterFeed = new TwitterFeed();
		mapper = new ObjectMapper();
	}

	public Handler(TwitterFeed twitterFeed, ObjectMapper mapper) {
		this.twitterFeed = twitterFeed;
		this.mapper = mapper;
	}

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Request request, Context context) {
		LOG.info("received: " + request.getName());
		if(request.getName() == null) {
			request.setName("FreeHackney");
		}
		List<Status> tweets = twitterFeed.readFeed(request);
		String tweetsStrings = "";
		try {
			tweetsStrings = mapper.writeValueAsString(tweets);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Response responseBody = new Response(tweetsStrings);
		LOG.info("response: " + tweetsStrings);
		Map<String, String> headers = new HashMap<>();
		headers.put("X-Powered-By", "AWS Lambda & Serverless");
		headers.put("Content-Type", "application/json");
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(headers)
				.build();
	}
}
