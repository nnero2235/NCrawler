package nnero.ncrawler.util;

import java.security.MessageDigest;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/2/4 下午10:46
 * <p/>
 * Function: to use this util for getting md5 string.
 * <p/>
 * ************************************************
 */
public class MD5 {
    public static final char HEX_DIGITS[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String generate(String s){

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            NLog.d(e.getMessage());
            return null;
        }
    }
}
