import com.serverless.Handler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by ellioe03 on 13/06/2017.
 * What behaviours do we need for a server?
 * Implement part of Jetty api.
 * Handler input into httpServerlet - done
 * Handles a GET request, reads set twitter feed
 */
@RunWith(MockitoJUnitRunner.class)
public class LauncherTest {


    @Mock Handler handler;
    Launcher launcher = new Launcher();


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void doGetTest() throws Exception {
        //Want to return HEADER and BODY same as Handler
        //Mock handler and test and implement doGet method

        //test wiring using verify

        //use cucumber to test server








    }

}