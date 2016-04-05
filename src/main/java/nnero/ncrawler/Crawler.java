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
public class Crawler extends BaseCrawler{


    private PageProcessor mPageProcessor;


    public Crawler(PageProcessor pageProcessor){
        super();
        this.mPageProcessor = pageProcessor;
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


    @Override
    protected void process(Page page) {
        mPageProcessor.process(page);
    }
}
