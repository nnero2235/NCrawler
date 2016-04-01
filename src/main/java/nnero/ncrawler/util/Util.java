package nnero.ncrawler.util;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 下午2:17
 * <p>
 * Function:util for project
 * <p>
 * ************************************************
 */
public class Util {

    /**
     * format long type time to format string.
     * just like 1days 2hours 14mins 11secs.
     * @param time
     * @return
     */
    public static String formatMilliTime(long time){
        long day = time/1000/60/60/24;
        long hour = time/1000/60/60%24;
        long min = time/1000/60%60;
        long sec = time/1000%60;

        return day+"days "+hour+"hours "+min+"mins "+sec+"secs";
    }
}
