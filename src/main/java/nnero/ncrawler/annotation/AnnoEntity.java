package nnero.ncrawler.annotation;

import java.lang.annotation.*;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 下午6:04
 * <p>
 * Function: entity class must has this annotation.
 * <p>
 * ************************************************
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnoEntity {
}
