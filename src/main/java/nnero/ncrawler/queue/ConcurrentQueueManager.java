package nnero.ncrawler.queue;

import nnero.ncrawler.entity.Link;
import nnero.ncrawler.queue.core.LinkQueue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 下午12:05
 * <p/>
 * Function:thread safe queue manager
 * <p/>
 * ************************************************
 */
public class ConcurrentQueueManager implements QueueManager {

    private LinkQueue<Link> mWaitQueue;
    private ReentrantLock mLock;
    private DuplicateLinkRemover mDuplicateRemover;

    public ConcurrentQueueManager(){
        this.mWaitQueue = new LinkQueue<>();
        this.mDuplicateRemover = new DuplicateLinkRemover();
        this.mLock = new ReentrantLock();
    }

    @Override
    public void push(Link link) {
        try {
            mLock.lock();
            if(!mDuplicateRemover.isDuplicate(link.getUrl())) {
                mWaitQueue.push(link);
            }
        } finally {
            mLock.unlock();
        }
    }

    @Override
    public Link poll() {
        Link link = null;
        try {
            mLock.lock();
            link = mWaitQueue.poll();
        } finally {
            mLock.unlock();
        }
        return link;
    }

    @Override
    public boolean isEmpty() {
        return mWaitQueue.size() == 0;
    }

    @Override
    public long getQueueSize() {
        return mWaitQueue.size();
    }

    @Override
    public long getAlreadyFetchUrlNumber() {
        return mDuplicateRemover.getSize();
    }
}
