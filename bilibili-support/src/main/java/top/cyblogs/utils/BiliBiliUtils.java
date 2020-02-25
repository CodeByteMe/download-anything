package top.cyblogs.utils;


import top.cyblogs.data.BiliBiliData;
import top.cyblogs.exception.InternetException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * 下载器的公用方法类
 * 2019年7月15日
 */
public class BiliBiliUtils {

    /**
     * Content-Encoding
     */
    private static final String GZIP = "gzip";
    private static final String DEFLATE = "deflate";
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

    /**
     * 获取URL中的文本
     */
    public static String urlText(String url) {
        return urlText(url, BiliBiliData.header);
    }

    /**
     * 访问URL得到String
     *
     * @param url URL
     * @return String
     */
    public static String urlText(String url, Map<String, String> header) {
        return urlText(url, "GET", header);
    }

    public static String urlText(String url, String method, Map<String, String> header) {
        try {
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(method);
            header.forEach(connection::setRequestProperty);
            // 改变编码流
            String encoding = connection.getHeaderField("Content-Encoding");
            InputStream response = changeInputStream(connection.getInputStream(), encoding);
            // 变为字符串
            return new String(response.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternetException("网络异常，请重试...");
        }
    }

    /**
     * 将InputStream根据特定的编码进行转换
     *
     * @param input    InputStream
     * @param encoding 编码
     * @return 转换后的InputStream
     */
    private static InputStream changeInputStream(InputStream input, String encoding) throws IOException {
        if (GZIP.equalsIgnoreCase(encoding)) {
            return new GZIPInputStream(input);
        }
        if (DEFLATE.equalsIgnoreCase(encoding)) {
            return new InflaterInputStream(input, new Inflater(true));
        }
        return input;
    }
}
