package labs.example.config;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpReq {

    private URL url;

    private HttpURLConnection httpConnection;

    public HttpReq(URL url, HttpURLConnection httpConnection){
        this.httpConnection = httpConnection;
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public HttpURLConnection getHttpConnection() {
        return httpConnection;
    }

    public void setHttpConnection(HttpURLConnection httpConnection) {
        this.httpConnection = httpConnection;
    }
}
