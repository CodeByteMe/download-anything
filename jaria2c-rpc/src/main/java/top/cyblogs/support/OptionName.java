package top.cyblogs.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标识属性字段，让属性和Aria2c的选项对应上
 *
 * @author CY
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionName {

    /**
     * 选项的名字
     *
     * @return 选项的名字
     */
    String value();
}
