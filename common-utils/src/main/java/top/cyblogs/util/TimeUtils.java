package top.cyblogs.util;

/**
 * 该类用于对时间格式化的工具类
 */
public class TimeUtils {

    /**
     * 可读性好的时间
     *
     * @param second 秒数
     * @return 可读性好的时间（字符串）
     */
    public static String timeFormat(double second) {
        String format = "";
        int hour = (int) second / (60 * 60) % 60;
        int minutes = (int) second / (60) % 60;
        int seconds = (int) second % 60;
        format += hour == 0 ? "" : hour + "时";
        format += minutes == 0 ? "" : minutes + "分";
        format += seconds + "秒";
        return format;
    }
}