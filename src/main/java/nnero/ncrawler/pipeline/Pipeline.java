package nnero.ncrawler.pipeline;

import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午10:56
 * <p/>
 * Function:the pipeline will process the result when pageprocessor finish.
 *          e.g.  print on console. Or store data in database.
 *          A crawler can have more than one pipeline.These will process results by order.
 * <p/>
 * ************************************************
 */
public interface Pipeline {

    /**
     * deal with results
     * @since 0.1.0
     */
    void processResult(Page page);
}
