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
 * Time : 16/4/1 下午5:22
 * <p>
 * Function:extract content.
 *          regex expression save in it.
 *          backup is used when regex can't extract content then try backup.
 *          @since 0.2.0
 * <p>
 * ************************************************
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtractBy {
    String regex() default "";
    String backup() default "";
    int group() default 0;
    int backGroup() default 0;
}
