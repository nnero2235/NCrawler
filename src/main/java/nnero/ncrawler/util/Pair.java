package nnero.ncrawler.util;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/5 上午11:17
 * <p>
 * Function:a container of left and right Object.
 * <p>
 * ************************************************
 */
public class Pair<L,R> {
    public L l;
    public R r;

    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }
}
