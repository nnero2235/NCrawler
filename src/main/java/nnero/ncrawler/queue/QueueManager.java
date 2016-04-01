package nnero.ncrawler.queue;

import nnero.ncrawler.entity.Link;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午11:55
 * <p/>
 * Function:  queue manager : it will manage url queues.
 * <p/>
 * ************************************************
 */
public interface QueueManager {

    /**
     * push the target link in queue
     * @param link
     * @since 0.1.0
     */
    void push(Link link);

    /**
     * poll the store url in queue
     * @since 0.1.0
     */
    Link poll();

    /**
     * if queueManager's Queue has no elements return ture.
     * @since 0.1.0
     * @return
     */
    boolean isEmpty();

    /**
     * get queue size at present.
     * @since 0.1.0
     * @return
     */
    long getQueueSize();

    /**
     * get has fetched url number.
     * @since 0.1.0
     * @return
     */
    long getAlreadyFetchUrlNumber();
}
