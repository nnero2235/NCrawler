package nnero.ncrawler.pipeline;

import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;
import nnero.ncrawler.util.NLog;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午11:13
 * <p/>
 * Function:print result on Console.
 * <p/>
 * ************************************************
 */
public class ConsolePipeline implements Pipeline{

    @Override
    public void processResult(Page page) {
        NLog.d("console");
        Results results = page.getResults();
        for(Object k:results.getMap().keySet()){
            String key = (String) k;
            System.out.println(key+":"+results.get(key).toString());
        }
    }
}
