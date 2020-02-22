package top.cyblogs.util;

import java.util.List;

/**
 * 集合操作工具类
 *
 * @author CY
 */
public class CollectionUtils {

    /**
     * 列表加入分隔符
     *
     * @param list      列表
     * @param separator 分隔符
     * @return 结果
     */
    public static String join(List<?> list, String separator) {
        if (list == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, size = list.size(); i < size; i++) {
            stringBuilder.append(list.get(i));
            if (i == size - 1) {
                continue;
            }
            stringBuilder.append(separator);
        }
        return stringBuilder.toString();
    }
}
