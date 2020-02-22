package top.cyblogs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MoocUtils {

    /**
     * 解析icourse163的dwr，居然用dwr
     *
     * @param dwr    dwr文本
     * @param fields 所需要解析的属性
     * @return 解析后的List
     */
    public static List<Map<String, Object>> parseDwr(String dwr, String[] fields) {
        // 存储正在解析的变量
        String lastVar = "";
        // 存储结果
        List<Map<String, Object>> result = new ArrayList<>();
        // 临时存储一个解析结果
        HashMap<String, Object> keyValue = new HashMap<>();

        for (String statement : dwr.split(";")) {
            try {
                String[] lrVar = statement.split("=");
                for (String field : fields) {
                    if (lrVar[0].contains("." + field)) {
                        String value = lrVar[1].trim();
                        if (value.matches("\\d+")) {
                            keyValue.put(field, Long.parseLong(value));
                        } else if (value.contains("\\u")) {
                            keyValue.put(field, unicodeToString(value.substring(1, value.length() - 1)));
                        } else if ("null".equals(value)) {
                            keyValue.put(field, null);
                        } else {
                            keyValue.put(field, value);
                        }
                    }
                }
                String currentVar = lrVar[0].split("\\.")[0];
                if (!lastVar.equals(currentVar)) {
                    result.add(keyValue);
                    lastVar = currentVar;
                    keyValue = new HashMap<>();
                }
            } catch (Exception ignored) {
            }
        }
        return result.stream().filter(x -> x.size() > 0 && x.get("name") != null).collect(Collectors.toList());
    }


    /**
     * Unicode 2 String
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
