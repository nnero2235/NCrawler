package nnero.ncrawler.thread;

import sun.java2d.loops.RenderLoops;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/29 下午6:25
 * <p/>
 * Function: may block the threadpool when threads are all running.
 *          Avoiding to add too many tasks.
 * <p/>
 * ************************************************
 */
public class BlockThreadPool {

    private AtomicInteger mThreads;
    private ExecutorService mExecutor;
    private ReentrantLock mLock;
    private Condition mCondition;
    private int mMaxThreads;

    public BlockThreadPool(int num){
        this.mMaxThreads = num;
        this.mThreads = new AtomicInteger();
        this.mLock = new ReentrantLock();
        this.mExecutor = Executors.newFixedThreadPool(num);
        this.mCondition = mLock.newCondition();
    }

    public void execute(final Runnable r){
        if(mThreads.get() >= mMaxThreads){
            mLock.lock();
            try {
                while (mThreads.get() >= mMaxThreads){
                    try {
                        mCondition.await(); //block thread
                    } catch (InterruptedException e) {
                    }
                }
            } finally {
                mLock.unlock();
            }
        }
        mThreads.incrementAndGet();
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                r.run();

                try {
                    mLock.lock();
                    mThreads.decrementAndGet();
                    mCondition.signal(); //awake thread
                } finally {
                    mLock.unlock();
                }
            }
        });
    }
}
