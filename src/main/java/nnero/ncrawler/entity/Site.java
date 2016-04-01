package nnero.ncrawler.entity;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 上午9:38
 * <p>
 * Function:the web site
 * <p>
 * ************************************************
 */
public class Site {

    private String mDomain;//web address
    private int mPort; //default is 80

    private String mBaseUrl; //some target urls are based on this.
    private boolean isNeedContactBaseUrl;

    private Site(){
        this.mPort = 80;
        this.isNeedContactBaseUrl = true;
    }

    public static Site me(){
        return new Site();
    }

    public Site setDomain(String domain){
        this.mDomain = domain;
        return this;
    }

    /**
     * set base url
     * @since 0.1.0
     * @param baseUrl
     * @return
     */
    public Site setBaseUrl(String baseUrl){
        this.mBaseUrl = baseUrl;
        return this;
    }

    public String getBaseUrl(){
        return mBaseUrl;
    }

    public String getDomain(){
        return mDomain;
    }

    /**
     * set base url switch if needs to contact in prefix.
     * @param need
     * @since 0.1.0
     * @return
     */
    public Site setNeedContactBaseUrl(boolean need){
        this.isNeedContactBaseUrl = need;
        return this;
    }

    public boolean isNeedContactBaseUrl(){
        return isNeedContactBaseUrl;
    }
}
