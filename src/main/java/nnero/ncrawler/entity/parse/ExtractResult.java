package nnero.ncrawler.entity.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/3/31 上午10:24
 * <p>
 * Function: save extracting results.
 * <p>
 * ************************************************
 */
public class ExtractResult implements Parsable {
    private List<String> mResults = new ArrayList<>();

    public ExtractResult(){
    }

    /**
     * add result string
     * @param result
     */
    public void addResult(String result){
        mResults.add(result);
    }

    @Override
    public Parsable regex(String regex) {
        return regex(regex,0);
    }

    @Override
    public Parsable regex(String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        ExtractResult extractResult = new ExtractResult();
        for(String result : mResults){
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()){
                extractResult.addResult(matcher.group(group));
            }
        }
        return extractResult;
    }

    @Override
    public List<String> all() {
        return mResults;
    }

    @Override
    public Parsable addPrefix(String prefix) {
        //TODO: I has no way to do in temporary.
        return this;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(String result : mResults){
            sb.append(result);
        }
        return sb.toString();
    }
}
