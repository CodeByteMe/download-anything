package top.cyblogs.util;

import top.cyblogs.exception.InternetException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class HttpUtils {


    /**
     * Content-Encoding
     */
    private static final String GZIP = "gzip";
    private static final String DEFLATE = "deflate";

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
            throw new InternetException("网络异常...");
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
