package nnero.ncrawler.downloader;

import nnero.ncrawler.entity.Link;
import nnero.ncrawler.entity.Page;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午9:45
 * <p/>
 * Function: interface of the downloader
 * <p/>
 * ************************************************
 */
public interface Downloader {

    /**
     * down the target url
     * @param link
     */
    Page down(Link link);
}
