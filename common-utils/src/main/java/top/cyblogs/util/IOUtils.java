package top.cyblogs.util;

import java.io.*;

/**
 * 做IO操作的工具类
 * JDK版本低的时候使用，已经不用了
 */
public class IOUtils {

    /**
     * 文件的结尾
     */
    private static final int EOF = -1;

    /**
     * 默认buffer尺寸
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 把输入流转换到输出流中
     *
     * @param input  输入流
     * @param output 输出流
     */
    public static void copy(InputStream input, OutputStream output) throws IOException {
        if (input == null || output == null) {
            throw new IllegalArgumentException("参数异常");
        }
        int length;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while ((length = input.read(buffer)) != EOF) {
            output.write(buffer, 0, length);
        }
    }

    /**
     * 把输入流转换到输出流中
     *
     * @param input  输入流
     * @param output 输出流
     */
    public static void copy(InputStream input, RandomAccessFile output) throws IOException {
        if (input == null || output == null) {
            throw new IllegalArgumentException("参数异常");
        }
        int length;
        byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
        while ((length = input.read(bytes)) != EOF) {
            output.write(bytes, 0, length);
        }
    }

    /**
     * 读取所有的字节数组
     *
     * @param input 输入流
     * @return 所有的字节数组
     */
    public static byte[] readAllBytes(InputStream input) {
        BufferedInputStream bufferedInput = new BufferedInputStream(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = bufferedInput.read(buffer)) != EOF) {
                output.write(buffer, 0, length);
            }
            return output.toByteArray();
        } catch (IOException ignored) {
        } finally {
            try {
                output.close();
                bufferedInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }
}
