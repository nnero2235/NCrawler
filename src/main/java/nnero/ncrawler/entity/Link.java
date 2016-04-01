package nnero.ncrawler.entity;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午9:42
 * <p/>
 * Function: package the target url.
 *           And provides some convinent methods.
 * <p/>
 * ************************************************
 */
public class Link {

    private String mUrl;

    public Link(String url){
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

}
