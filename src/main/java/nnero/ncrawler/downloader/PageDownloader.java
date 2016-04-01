package nnero.ncrawler.downloader;

import jdk.nashorn.internal.ir.Block;
import nnero.ncrawler.downloader.http.OKClient;
import nnero.ncrawler.entity.Link;
import nnero.ncrawler.entity.Page;
import nnero.ncrawler.proccessor.PageProcessor;
import nnero.ncrawler.thread.BlockThreadPool;
import nnero.ncrawler.util.NLog;
import okhttp3.Response;

import java.io.IOException;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午9:47
 * <p/>
 * Function: the manager of the downloadTasks
 * <p/>
 * ************************************************
 */
public class PageDownloader implements Downloader{


    @Override
    public Page down(final Link link) {
        if(link == null){
            NLog.d("Downloader:link is null");
            return null;
        }
        try {
            Response response = OKClient.execute(link.getUrl());
            if(response.isSuccessful()) {
                Page page = new Page(response.body().string());
                return page;
            } else {
                //TODO:重新加入待下载队列
                return null;
            }
        } catch (IOException e) {
            NLog.d(e.getMessage());
            //TODO:重新加入待下载队列
            return null;
        }
    }
}
