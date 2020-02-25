package top.cyblogs.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 已经支持的URL枚举类
 */
@AllArgsConstructor
@Getter
@ToString
public enum SupportUrl {

    /**
     * BiliBili的支持
     */
    BILIBILI("bilibili.com");

    /**
     * 该类型的URL前缀
     */
    private String urlContains;
}
