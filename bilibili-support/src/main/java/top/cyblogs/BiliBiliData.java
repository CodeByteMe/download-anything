package top.cyblogs;

import top.cyblogs.data.HttpData;

import java.util.Map;

public class BiliBiliData {

    public static String cookie = "";

    public static Map<String, String> header = Map.of(
            "Origin", HttpData.BILIBILI_HOME_URL,
            "User-Agent", HttpData.USER_AGENT,
            "Referer", HttpData.BILIBILI_HOME_URL,
            "Cookie", BiliBiliData.cookie);
}
