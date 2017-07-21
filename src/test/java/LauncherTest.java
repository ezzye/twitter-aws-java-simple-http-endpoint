import com.amazon.speech.Sdk;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ellioe03 on 14/06/2017.
 */
public class LauncherTest {

    private Server server;
    private static final String CA_KEYSTORE_TYPE = KeyStore.getDefaultType(); //"JKS";
    private static final String CA_KEYSTORE_PATH = "/Library/Java/JavaVirtualMachines/jdk1.8.0_92.jdk/Contents/Home/jre/lib/security/keyStore.jks";
    private static final String CA_KEYSTORE_PASS = "Aelliott1963";

    @Before
    public void startJetty() throws Exception
    {
        String[] args = new String[0];
        Launcher.main(args);
    }

    @After
    public void stopJetty()
    {
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() throws Exception
    {
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(
                createSslCustomContext(),
                new String[]{"TLSv1","TLSv1.1","TLSv1.2"},
                Sdk.SUPPORTED_CIPHER_SUITES,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        try (CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(csf).build()) {
            HttpGet req = new HttpGet("https://localhost:8443/hello");
            try (CloseableHttpResponse response = httpclient.execute(req)) {
                assertThat(response.getStatusLine().toString(),is("HTTP/1.1 200 OK"));
            }
        }
    }

    public static SSLContext createSslCustomContext() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException {
        KeyStore tks = KeyStore.getInstance(CA_KEYSTORE_TYPE);
        tks.load(new FileInputStream(CA_KEYSTORE_PATH), CA_KEYSTORE_PASS.toCharArray());
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(tks, new TrustSelfSignedStrategy()) // use it to customize
                .build();
        return sslcontext;
    }

}