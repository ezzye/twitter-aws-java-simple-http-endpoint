package com.serverless;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

/**
 * Created by ellioe03 on 13/06/2017.
 */
public class TwitterFeedServlet extends HttpServlet {
    public TwitterFeedServlet() {
        this.servletTwitterFeedRequestHandler = new ServletTwitterFeedRequestHandler();
        this.mapper = new ObjectMapper();
    }

    private static final Logger log = LoggerFactory.getLogger(TwitterFeedServlet.class);

    private TwitterFeed twitterFeed;
    private transient ServletTwitterFeedRequestHandler servletTwitterFeedRequestHandler;
    private ObjectMapper mapper;



    private static final String LSTRING_FILE =
            "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings =
            ResourceBundle.getBundle(LSTRING_FILE);

    public void setServlet(TwitterFeed twitterFeed) {
        this.twitterFeed = twitterFeed;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
        }
        byte[] outputBytes = null;
        outputBytes  = servletTwitterFeedRequestHandler.handleServletCall(twitterFeed,mapper);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        try (final OutputStream out = resp.getOutputStream()) {
            resp.setContentLength(outputBytes.length);
            out.write(outputBytes);
        }

    }



}
