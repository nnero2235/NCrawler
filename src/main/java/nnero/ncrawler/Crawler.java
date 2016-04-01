package nnero.ncrawler;

import nnero.ncrawler.downloader.Downloader;
import nnero.ncrawler.downloader.PageDownloader;
import nnero.ncrawler.entity.Link;
import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;
import nnero.ncrawler.entity.Site;
import nnero.ncrawler.pipeline.ConsolePipeline;
import nnero.ncrawler.pipeline.Pipeline;
import nnero.ncrawler.proccessor.PageProcessor;
import nnero.ncrawler.queue.ConcurrentQueueManager;
import nnero.ncrawler.queue.QueueManager;
import nnero.ncrawler.thread.BlockThreadPool;
import nnero.ncrawler.util.MD5;
import nnero.ncrawler.util.NLog;
import nnero.ncrawler.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/29 下午6:19
 * <p/>
 * Function:crawler main controller
 * <p/>
 * ************************************************
 */
public class Crawler implements CrawlerTask{

    private static final long QUEUE_WAIT_TIME = 10000L;

    private String mTaskId;
    private long mTime = 500L; //download page Interval time and only use in multi threads.
    private BlockThreadPool mThreadPool;
    private PageProcessor mPageProcessor;
    private Downloader mDownloader;
    private List<Pipeline> mPipelines;
    private QueueManager mQueueManager;
    private Site mSite;
    private long mWaitTime;

    private static boolean isShutDown;

    public Crawler(PageProcessor pageProcessor){
        this.mPageProcessor = pageProcessor;
        this.mQueueManager = new ConcurrentQueueManager();
        this.mDownloader = new PageDownloader();
        this.mTaskId = "Crawler:"+ MD5.generate(this.toString());
    }

    /**
     * create crawler by pageproccessor
     * @return
     * @since 0.1.0
     */
    public static Crawler create(PageProcessor pageProcessor){
        return new Crawler(pageProcessor);
    }

    /**
     * set downloader which maybe your own downloader
     * @param downloader
     * @since 0.1.0
     */
    public Crawler setDownloader(Downloader downloader){
        this.mDownloader = downloader;
        return this;
    }

    /**
     * set the base site info.
     * @param site
     * @since 0.1.0
     * @return
     */
    public Crawler site(Site site){
        this.mSite = site;
        return this;
    }

    /**
     * set multithreads with num
     * @param num
     * @since 0.1.0
     * @return
     */
    public Crawler thread(int num){
        mThreadPool = new BlockThreadPool(num);
        return this;
    }

    /**
     * set the interval time
     * @param time
     * @since 0.1.0
     * @return
     */
    public Crawler time(long time){
        mTime = time;
        return this;
    }

    /**
     * add start url
     * this method can invoke more than once
     * all url will be added to the queue.
     * @since 0.1.0
     * @param url
     * @return
     */
    public Crawler addStartLink(String url){
        mQueueManager.push(new Link(url));
        return this;
    }

    /**
     * add pipeline to deal with results
     * @param pipeline
     * @since 0.1.0
     * @return
     */
    public Crawler addPipeline(Pipeline pipeline){
        if(mPipelines == null){
            mPipelines = new ArrayList<Pipeline>();
        }
        mPipelines.add(pipeline);
        return this;
    }

    /**
     * set the customized queueManager instead of the default.
     * note: the method must be invoked before add start urls.
     * @param queueManager
     * @since 0.1.0
     * @return
     */
    public Crawler setQueueManager(QueueManager queueManager){
        this.mQueueManager = queueManager;
        return this;
    }

    /**
     * stop the crawler
     * @since 0.1.0
     */
    public static void stop(){
       isShutDown = true;
    }

    @Override
    public String getTaskID() {
        return mTaskId;
    }

    @Override
    public void run() {
        if(mSite == null){
            throw new RuntimeException("must set web site!");
        }

        if(mPipelines == null){
            mPipelines = new ArrayList<>();
            mPipelines.add(new ConsolePipeline());
        }

        NLog.d("crawler start!");
        long startTime = System.currentTimeMillis();
        while (!isShutDown && mWaitTime < QUEUE_WAIT_TIME) {
            if(!mQueueManager.isEmpty()) {
                try {
                    Thread.sleep(mTime);
                } catch (InterruptedException e) {
                }
                if (mThreadPool != null) { //multi threads
                    mThreadPool.execute(new MultiThreadTask());
                } else { //single thread
                    doCrawler();
                }
            } else {
                //wait util mQueueManager has element but has limit time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                mWaitTime += 1000L;
                NLog.d("crawler wait for queue");
            }
        }
        NLog.d("crawler stop!");
        long costTime = System.currentTimeMillis() - startTime;
        NLog.fatal("costTime: "+ Util.formatMilliTime(costTime));
    }
    //use this just because I don't like anonymous class.
    private class MultiThreadTask implements Runnable{
        @Override
        public void run() {
            doCrawler();
        }
    }

    //this method describes crawler's working flows.
    private void doCrawler(){
        NLog.trace("down...");
        Page page = mDownloader.down(mQueueManager.poll());
        if (page != null) {
            NLog.trace("process...");
            mPageProcessor.process(page);
            NLog.trace("push url...");
            pushUrls(page);
            for (Pipeline pipeline : mPipelines) {
                NLog.trace("pipeline...");
                pipeline.processResult(page);
            }
        }
    }

    private void pushUrls(Page page){
        if(page.getTargetUrls() != null) {
            for (String url : page.getTargetUrls()) {
                if(mSite.isNeedContactBaseUrl()) {
                    mQueueManager.push(new Link(mSite.getBaseUrl()+url));
                } else {
                    mQueueManager.push(new Link(url));
                }
            }
            NLog.trace("queue size:"+mQueueManager.getQueueSize());
        } else {
            NLog.fatal("the page has no urls.");
        }
    }
}
