package nnero.ncrawler.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 上午11:00
 * <p/>
 * Function: papeprocessor will create this when finish.
 * <p/>
 * ************************************************
 */
public class Results<T> {

    private Map<String,T> mMap;

    public Results(){
        mMap = new HashMap<>();
    }

    public void put(String key,T target){
        mMap.put(key,target);
    }

    public T get(String key){
        return mMap.get(key);
    }

    public Map<String,T> getMap(){
        return mMap;
    }
}
