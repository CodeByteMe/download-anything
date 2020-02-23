package top.cyblogs.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author CY
 */
public class StringUtils {

    /**
     * 判断字符串是否不为Empty
     *
     * @param value 字符串
     * @return false : 如果字符串为null或者为空串
     * true : 如果字符串不为null或者空串
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !"".equals(value.trim());
    }

    /**
     * 判断字符串是否不为数字
     *
     * @param value 字符串
     * @return false : 如果字符串为数字
     * true : 如果字符串不为数字
     */
    public static boolean isNotNumber(String value) {
        return !value.matches("\\d+");
    }

    /**
     * 得到两个字符串中间的字符串
     *
     * @param line  字符串
     * @param start 开始字符串
     * @param end   结束字符串
     * @return 开始和结束中间的字符
     */
    public static String subString(String line, String start, String end) {
        // 给开始和结束的字符串进行转义
        String startQuote = Pattern.quote(start);
        String endQuote = Pattern.quote(end);
        // 这里必须用非贪婪模式
        String regexp = startQuote + "(.*?)" + endQuote;
        Matcher matcher = Pattern.compile(regexp).matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    /**
     * 写一个md5加密的方法
     */
    public static String md5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        // 16进制数字
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString();
    }

    /**
     * 获取一个随机的UUID
     *
     * @return 一个随机的UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}