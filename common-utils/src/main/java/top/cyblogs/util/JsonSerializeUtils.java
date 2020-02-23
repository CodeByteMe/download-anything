package top.cyblogs.util;

import java.io.*;
import java.util.stream.Collectors;

/**
 * 对象Json序列化工具类（不适用于大对象）
 *
 * @author CY
 */
public class JsonSerializeUtils {

    /**
     * 将一个对象序列化到硬盘中
     *
     * @param t    将要序列化的对象
     * @param path 序列化文件保存的路径
     */
    public synchronized static <T> void serialize(T t, String path) {

        // 先创建该目录，以防报错
        FileUtils.mkdirs(new File(path));

        try (FileOutputStream out = new FileOutputStream(path)) {
            serialize(t, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将一个对象序列化到硬盘中
     *
     * @param t   将要序列化的对象
     * @param out 序列化文件的输出流
     */
    public synchronized static <T> void serialize(T t, OutputStream out) {

        // 如果对象为null的话，就不参与序列化
        if (t == null || out == null) {
            return;
        }

        // 开始进行序列化
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            writer.write(JsonUtils.toStringIgnoreNull(t));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 反序列化一个文件，使其恢复为一个对象
     *
     * @return 反序列化后的对象
     */
    public synchronized static <T> T deserialize(String path, Class<T> clazz) {
        // 判断文件是否存在
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        try (FileInputStream input = new FileInputStream(file)) {
            return deserialize(input, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化一个文件，使其恢复为一个对象
     *
     * @return 反序列化后的对象
     */
    public synchronized static <T> T deserialize(InputStream input, Class<T> clazz) {

        // 如果对象为null的话，就不参与序列化
        if (input == null || clazz == null) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            return JsonUtils.toObject(reader.lines().collect(Collectors.joining()), clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}