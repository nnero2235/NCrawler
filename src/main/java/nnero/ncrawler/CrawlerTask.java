package nnero.ncrawler;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/29 下午6:20
 * <p/>
 * Function:爬虫任务接口
 * <p/>
 * ************************************************
 */
public interface CrawlerTask extends Runnable{

    /**
     * 获得唯一的taskId
     * @since 0.1.0
     * @return
     */
    String getTaskID();
}
