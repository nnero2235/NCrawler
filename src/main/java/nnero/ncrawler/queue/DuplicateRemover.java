package nnero.ncrawler.queue;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/3/30 下午5:14
 * <p>
 * Function: the interface of duplicate urls
 * <p>
 * ************************************************
 */
public interface DuplicateRemover {

    /**
     * judge the url whether has crawlerd
     * @param url
     * @since 0.1.0
     * @return
     */
    boolean isDuplicate(String url);

    /**
     * get set's size
     * @since 0.1.0
     * @return
     */
    long getSize();
}
