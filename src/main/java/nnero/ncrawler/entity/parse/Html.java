package nnero.ncrawler.entity.parse;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/3/30 下午6:01
 * <p>
 * Function: describe the html dom.
 * <p>
 * ************************************************
 */
public class Html implements Parsable {

    private String mText;

    public Html(String text){
        this.mText = text;
    }

    /**
     * get text
     * @since 0.1.0
     * @return
     */
    public String getText(){
        return mText;
    }

    /**
     * return all href element links
     * @since 0.1.0
     * @return
     */
    public Parsable links(){
        return regex("href=\"(.+?)\"",1);
    }

    @Override
    public Parsable regex(String regex) {
        return regex(regex,0);
    }

    @Override
    public Parsable regex(String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mText);
        ExtractResult extractResult = new ExtractResult();
        while (matcher.find()){
            extractResult.addResult(matcher.group(group));
        }
        return extractResult;
    }

    @Override
    public List<String> all() {
        return Lists.newArrayList(mText);
    }

    @Override
    public Parsable addPrefix(String prefix) {
        //no need
        return null;
    }
}
