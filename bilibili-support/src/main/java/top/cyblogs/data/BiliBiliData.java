package top.cyblogs.data;

import java.util.Map;

public class BiliBiliData {

    public static final String SOURCE = "哔哩哔哩";
    public static String cookie = "";
    public static Map<String, String> header = Map.of(
            "Origin", HttpData.BILIBILI_HOME_URL,
            "User-Agent", HttpData.USER_AGENT,
            "Referer", HttpData.BILIBILI_HOME_URL,
            "Cookie", BiliBiliData.cookie);
}
