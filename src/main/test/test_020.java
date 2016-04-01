import nnero.ncrawler.AnnoCrawler;
import nnero.ncrawler.entity.Site;
import org.junit.Test;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 下午5:27
 * <p>
 * Function: test for 0.2.0 version
 * <p>
 * ************************************************
 */
public class test_020 {

    @Test
    public void testAnnotationCrawler(){
        AnnoCrawler.create(new OsChinaJop())
                .site(Site.me().setBaseUrl("http://job.oschina.net/search/java"))
                .time(1000)
                .addPipeline(new OsChinaJop.JobConsolePipeLine())
                .thread(5)
                .run();
    }
}
