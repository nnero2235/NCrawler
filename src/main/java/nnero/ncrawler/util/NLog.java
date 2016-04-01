package nnero.ncrawler.util;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/3/30 下午5:23
 * <p>
 * Function: debug logs
 * <p>
 * ************************************************
 */
public class NLog {

    private static boolean isDebug = true;
    private static boolean isTrace = true;

    public static void debug(boolean isDebug){
        NLog.isDebug = isDebug;
    }

    public static void d(String msg){
        if(isDebug)
            System.out.println(msg);
    }

    public static void trace(String msg){
        if(isTrace)
            System.out.println(msg);
    }

    public static void fatal(String msg){
        System.err.println(msg);
    }
}
