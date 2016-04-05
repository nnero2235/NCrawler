import nnero.ncrawler.annotation.ExtractBy;
import nnero.ncrawler.annotation.TargetUrl;
import nnero.ncrawler.entity.anno.AnnotationEntity;
import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;
import nnero.ncrawler.pipeline.Pipeline;

import java.util.List;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 下午5:29
 * <p>
 * Function: annotation way to craw oschina.
 * <p>
 * ************************************************
 */
@TargetUrl("\\?type=.+?&key=Java.+")
public class OsChinaJob extends AnnotationEntity{

    @ExtractBy( regex = "<a.+?h4 name over-hide.+?highlight\">(.+?)<",
                backup = "<a.+?h4 name over-hide.+?>(.+?)<",
                group = 1,
                backGroup = 1)
    private String name;

    @ExtractBy(regex = "<span.+?city\">(.+)",group = 1)
    private String city;

    @ExtractBy(regex = "<span.+?h4 rmb\">(.+?)<",group = 1)
    private String money;

    @ExtractBy(regex = "<span> +?经验(.+?)/",group = 1)
    private String experience;

    @ExtractBy(regex = "<span>.+?/ +?学历(.+?)<",group = 1)
    private String education;

    @ExtractBy(regex = "<a.+?h4 title over-hide\">(.+)",group = 1)
    private String company;

    public OsChinaJob(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "[-------------------------\n"+
                "    name:"+name+" city:"+city+"\n"+
                "    money:"+money+" experience:"+experience+" education:"+education+"\n"+
                "    company:"+company+"\n"+
                "--------------------------]";
    }

    static class JobConsolePipeLine implements Pipeline {

        @Override
        @SuppressWarnings("unchecked")
        public void processResult(Page page) {
            List<OsChinaJob> jobList = page.getAnnoResults().getObjects();
            for(OsChinaJob j:jobList){
                System.out.println(j.toString());
            }
        }
    }
}
