import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ellioe03 on 13/06/2017.
 * What behaviours do we need for a server?
 * Implement part of Jetty api.
 * Handler input into httpServerlet - done
 * Handles a GET request, reads set twitter feed
 */

//public class JUnitBeforeAfterJettyTest  {
//
//
//    private Server server;
//
//    @Before
//    public void startJetty() throws Exception
//    {
//        String[] args = new String[0];
//        Launcher.main(args);
//
//    }
//
//    @After
//    public void stopJetty()
//    {
//        try
//        {
//            server.stop();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGet() throws Exception
//    {
//        // Test GET
//        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:9999/").openConnection();
//        http.connect();
//        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
//    }
//
//
//
//
//}
