package top.cyblogs.data;

import java.util.Map;

/**
 * BiliBili的数据
 */
public class BiliBiliData {
    /**
     * 来源
     */
    public static final String SOURCE = "哔哩哔哩";
    /**
     * BiliBili首页
     */
    public static final String HOME_URL = "https://www.bilibili.com/";
    /**
     * 当前的Cookie
     */
    public static String cookie = "";
    /**
     * 每次请求的访问头
     */
    public static Map<String, String> header = Map.of(
            "Origin", HOME_URL,
            "User-Agent", HttpData.USER_AGENT,
            "Referer", HOME_URL,
            "Cookie", cookie);
}
