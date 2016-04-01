package nnero.ncrawler.entity.parse;

import java.util.List;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/3/30 下午6:10
 * <p>
 * Function: something can be parsed
 * <p>
 * ************************************************
 */
public interface Parsable {

    /**
     * regex parse.
     * default group number == 0
     * @param regex
     * @since 0.1.0
     * @return
     */
    Parsable regex(String regex);

    /**
     * regex parse and refering the group number.
     * @since 0.1.0
     * @param regex
     * @param group
     * @return
     */
    Parsable regex(String regex,int group);

    /**
     * return the parse results with a list.
     * @since 0.1.0
     * @return
     */
    List<String> all();

    /**
     * add prefix for results
     * @param prefix
     * @since 0.1.0
     * @return
     */
    Parsable addPrefix(String prefix);

    /**
     * get String result
     * @since 0.1.0
     * @return
     */
    String toString();
}
