package nnero.ncrawler.downloader.http;

import nnero.ncrawler.util.NLog;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午9:54
 * <p/>
 * Function:package the okhttp apis.
 * <p/>
 * ************************************************
 */
public class OKClient {

    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)"
    };

    private static final OkHttpClient sClient = new OkHttpClient();

    private static int sUserAgentPos = 0;
    /**
     * execute http request using get method.
     * @param url
     * @return okhttp response
     * @since 0.1.0
     */
    public static Response execute(String url) throws IOException {
        NLog.trace("URL:"+url);
        Request request = new Request.Builder()
                .get()
                .addHeader("User-Agent",USER_AGENTS[(sUserAgentPos++)%USER_AGENTS.length])
                .url(url)
                .build();
        return sClient.newCall(request).execute();
    }
}
