package nnero.ncrawler.entity;

import nnero.ncrawler.entity.parse.Html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午9:34
 * <p/>
 * Function: this class is the downloaded result which package some useful infos.
 *           page also saves the process results. Thus can be passed to Pipelines.
 *           e.g.  The HTML page
 *
 * <p/>
 * ************************************************
 */
public class Page {

    private Html html;
    private List<String> mTargetUrls;
    private Results mResults;
    private AnnoResults mAnnoResults;

    public Page(String html){
        this.html = new Html(html);
    }

    /**
     * add target urls
     * @since 0.1.0
     * @param urls
     */
    public void addTargetUrls(List<String> urls){
        this.mTargetUrls = urls;
    }

    /**
     * set target results
     * @since 0.1.0
     * @param mResults
     */
    public void setResults(Results mResults){
        this.mResults = mResults;
    }

    /**
     * get results
     * @since 0.1.0
     * @return
     */
    public Results getResults(){
        return mResults;
    }

    /**
     * get target urls if added.
     * @since 0.1.0
     * @return
     */
    public List<String> getTargetUrls(){
        return mTargetUrls;
    }

    /**
     * get html String
     * @since 0.1.0
     * @return
     */
    public Html getHtml(){
        return html;
    }

    /**
     * get the origin page text info without parsing.
     * @since 0.1.0
     * @return
     */
    public String getRawText(){
        return html.getText();
    }

    /**
     * annotation ways will use this
     * @since 0.2.0
     * @return
     */
    public AnnoResults getAnnoResults() {
        return mAnnoResults;
    }

    /**
     * annotation ways will use this.
     * @since 0.2.0
     * @param annoResults
     */
    public void setAnnoResults(AnnoResults annoResults) {
        this.mAnnoResults = annoResults;
    }
}
