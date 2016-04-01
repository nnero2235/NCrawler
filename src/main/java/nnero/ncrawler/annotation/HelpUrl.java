package nnero.ncrawler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 下午5:21
 * <p>
 * Function:help url is the url which help program finding targetUrls.
 *          @since 0.2.0
 * <p>
 * ************************************************
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HelpUrl {
    String value() default "";
}
