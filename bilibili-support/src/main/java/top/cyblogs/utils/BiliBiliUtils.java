package top.cyblogs.utils;


import top.cyblogs.BiliBiliData;
import top.cyblogs.util.HttpUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下载器的公用方法类
 * 2019年7月15日
 */
public class BiliBiliUtils {

    private static Pattern PLAY_ID = Pattern.compile("\\d+");

    /**
     * 获取播放的ID OK
     *
     * @return 播放ID
     */
    public static String getPlayId(String url) {
        try {
            String path = new URL(url).getPath();
            Matcher matcher = PLAY_ID.matcher(path);
            if (matcher.find()) {
                return matcher.group(0);
            }
        } catch (MalformedURLException ignored) {
        }
        return null;
    }

    public static String urlText(String url) {
        return HttpUtils.urlText(url, BiliBiliData.header);
    }
}
