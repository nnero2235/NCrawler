package nnero.ncrawler.proccessor;

import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午9:31
 * <p/>
 * Function: process the page(e.g HTML page) which download
 * <p/>
 * ************************************************
 */
public interface PageProcessor {

    /**
     * process the downloaded page
     * @param page
     * @since 0.1.0
     */
    void process(Page page);
}
