import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;
import nnero.ncrawler.entity.parse.Parsable;
import nnero.ncrawler.pipeline.Pipeline;
import nnero.ncrawler.proccessor.PageProcessor;
import nnero.ncrawler.util.NLog;

import java.util.ArrayList;
import java.util.List;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 下午2:10
 * <p/>
 * Function: use oschina as test website.
 * <p/>
 * ************************************************
 */
public class OsChinaJopProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //解析职位信息
        List<String> jobs = page.getHtml().regex("<a.+?h4 name over-hide.+?highlight\">(.+?)<", 1).all();
        //解析地点信息
        List<String> citys = page.getHtml().regex("<span.+?city\">(.+)", 1).all();
        //解析薪资信息
        List<String> moneys = page.getHtml().regex("<span.+?h4 rmb\">(.+?)<", 1).all();
        //解析经验
        List<String> experiences = page.getHtml().regex("<span> +?经验(.+?)/", 1).all();
        //解析学历
        List<String> educations = page.getHtml().regex("<span>.+?/ +?学历(.+?)<", 1).all();
        //解析公司
        List<String> companies = page.getHtml().regex("<a.+?h4 title over-hide\">(.+)", 1).all();

        List<String> links = page.getHtml()
                .links()
                .regex("\\?type=.+?&key=Java.+")
                .all();

        int size = jobs.size();

        Results<List<Job>> results = new Results<>();
        List<Job> jobList = new ArrayList<>();
        for(int i=0;i<size;i++){
            Job job = new Job();
            job.name = jobs.get(i).trim();
            job.city = citys.get(i).trim();
            job.money = moneys.get(i).trim();
            job.experience = experiences.get(i).trim();
            job.education = educations.get(i).trim();
            job.company = companies.get(i).trim();
            jobList.add(job);
        }
        results.put("job_list",jobList);
        page.setResults(results);
        page.addTargetUrls(links);
    }

    static class Job{
        String name;
        String city;
        String money;
        String experience;
        String education;
        String company;

        @Override
        public String toString() {
            return "[-------------------------\n"+
                    "    name:"+name+" city:"+city+"\n"+
                     "    money:"+money+" experience:"+experience+" education:"+education+"\n"+
                     "    company:"+company+"\n"+
                    "--------------------------]";
        }
    }

    static class JobConsolePipeLine implements Pipeline{

        @Override
        @SuppressWarnings("unchecked")
        public void processResult(Page page) {
            Results<List<Job>> jobs = page.getResults();
            List<Job> jobList = jobs.get("job_list");
            for(Job j:jobList){
                System.out.println(j.toString());
            }
        }
    }
}
