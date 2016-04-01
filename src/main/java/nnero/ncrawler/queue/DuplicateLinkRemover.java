package nnero.ncrawler.queue;

import com.sun.org.apache.bcel.internal.generic.DUP;

import java.util.HashSet;
import java.util.Set;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 下午12:03
 * <p/>
 * Function: remove the link that downloaded or already add in queue
 *           just use the most simple method that is using hashSet.
 * <p/>
 * ************************************************
 */
public class DuplicateLinkRemover implements DuplicateRemover {

    private Set<String> mUrls;

    public DuplicateLinkRemover(){
        this.mUrls = new HashSet<>();
    }

    @Override
    public boolean isDuplicate(String url){
        return !mUrls.add(url);
    }

    @Override
    public long getSize() {
        return mUrls.size();
    }
}
