package top.cyblogs.ffmpeg.utils;

import top.cyblogs.ffmpeg.listener.FFMpegListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 正在写FFMpeg进度条，项目中要添加下载列表选择，并且使用Vue-cli改写前端...
 */
public class ProgressUtils {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SS");
    private final Pattern TIME_PATTERN = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{2}");
    private AtomicLong totalTime = new AtomicLong(0);

    public void watchTimeProgress(String s, FFMpegListener listener) {
        if (listener == null) {
            return;
        }
        long currentTime;
        Matcher matcher = TIME_PATTERN.matcher(s);
        if (matcher.find()) {
            try {
                currentTime = DATE_FORMAT.parse(matcher.group(0)).getTime() + 28800000;
                if (s.contains("Duration")) {
                    totalTime.set(currentTime);
                } else {
                    listener.progress(currentTime, totalTime.get());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
