package com.serverless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;

/**
 * Created by ellioe03 on 13/06/2017.
 */
public class HandlerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(HandlerServlet.class);

    private Handler handler;

    public void setTwitterlet(Handler handler) {
    }
}
