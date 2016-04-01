import nnero.ncrawler.Crawler;
import nnero.ncrawler.entity.Site;
import nnero.ncrawler.queue.core.LinkQueue;
import org.junit.Test;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午10:28
 * <p/>
 * Function:0.1.0 version test
 * <p/>
 * ************************************************
 */
public class test_010 {
    @Test
    public void testSingleThread(){
        Crawler.create(new OsChinaJopProcessor())
                .addStartLink("http://www.oschina.net")
                .site(Site.me().setBaseUrl("http://www.oschina.net"))
                .time(1000)
                .run();
    }

    @Test
    public void testMultiThreads(){
        Crawler.create(new OsChinaJopProcessor())
                .addStartLink("http://job.oschina.net/search/java")
                .site(Site.me().setBaseUrl("http://job.oschina.net/search/java"))
                .time(1000)
                .addPipeline(new OsChinaJopProcessor.JobConsolePipeLine())
                .run();
    }

    @Test
    public void testLinkQueue(){
        LinkQueue<String> msgs = new LinkQueue<>();
        msgs.push("aaa");
        msgs.push("bbb");
        msgs.push("ccc");
        msgs.push("ddd");
        while (msgs.size() > 0){
            System.out.println(msgs.poll());
        }
    }
}
