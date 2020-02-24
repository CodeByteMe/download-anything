package top.cyblogs.ffmpeg.utils;

import top.cyblogs.ffmpeg.listener.FFMpegListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 监控FFMpeg进度信息
 *
 * @author CY
 */
public class ProgressUtils {

    /**
     * 解析当前进度中的时间
     */
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SS");

    /**
     * 匹配带有时间的字符串
     */
    private final Pattern TIME_PATTERN = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{2}");

    /**
     * 当前项目的总时间
     */
    private long totalTime = 0;

    /**
     * 监控时间进度
     */
    public void watchTimeProgress(String s, FFMpegListener listener) {
        if (listener == null) {
            return;
        }
        long currentTime;
        Matcher matcher = TIME_PATTERN.matcher(s);
        if (matcher.find()) {
            try {
                currentTime = DATE_FORMAT.parse(matcher.group(0)).getTime() + 28800000;
                String totalTimePrefix = "Duration";
                if (s.contains(totalTimePrefix)) {
                    totalTime = currentTime;
                } else {
                    listener.progress(currentTime, totalTime);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}