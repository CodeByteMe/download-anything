package top.cyblogs.init;

import java.io.*;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 压缩工具包
 */
public class InitializeUtils {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * 下载
     */
    public static FileInputStream download(InputStream input, File output, Consumer<Long> consumer) throws IOException {
        try (FileOutputStream out = new FileOutputStream(output)) {
            Objects.requireNonNull(out, "out");
            long transferred = 0;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int read;
            while ((read = input.read(buffer, 0, DEFAULT_BUFFER_SIZE)) >= 0) {
                out.write(buffer, 0, read);
                transferred += read;
                if (consumer != null) {
                    consumer.accept(transferred);
                }
            }
        }
        return new FileInputStream(output);
    }

    /**
     * 解压指定文件到指定地点
     */
    public static void unZip(InputStream inputStream, File out) throws IOException {
        try (ZipInputStream input = new ZipInputStream(new BufferedInputStream(inputStream));
             OutputStream output = new FileOutputStream(out)) {
            ZipEntry zipEntry;
            while ((zipEntry = input.getNextEntry()) != null) {
                if (zipEntry.getName().contains(out.getName())) {
                    input.transferTo(output);
                    return;
                }
            }
        }
    }
}
