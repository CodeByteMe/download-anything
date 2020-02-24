package top.cyblogs.ffmpeg.exec;

import top.cyblogs.ffmpeg.command.FFMpegCommand;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.ffmpeg.utils.ExecUtils;
import top.cyblogs.ffmpeg.utils.ProgressUtils;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载M3U8
 *
 * @author CY 测试通过
 */
public class DownloadM3U8 {

    private static final ThreadLocal<ExecutorService> EXECUTOR = ThreadLocal.withInitial(() -> Executors.newFixedThreadPool(10));

    /**
     * 解析M3U8下载视频
     * [不会产生临时文件]
     *
     * @param m3U8Url  M3U8的URL可以是File
     * @param out      输出的路径
     * @param listener 监听器
     */
    public static void exec(String m3U8Url, File out, FFMpegListener listener) {
        EXECUTOR.get().submit(() -> download(m3U8Url, out, listener));
    }

    private static void download(String m3U8Url, File out, FFMpegListener listener) {
        // 判断参数是否正确
        if (!StringUtils.isNotEmpty(m3U8Url) || out == null) {
            throw new IllegalArgumentException("下载M3U8时参数异常!");
        }

        if (listener != null) {
            listener.start();
        }

        // 建立目标文件夹
        FileUtils.mkdirs(out);

        // 先删除后下载
        FileUtils.deleteOnExists(out);

        // 获取命令
        List<String> command = FFMpegCommand.downloadM3U8(m3U8Url, out);

        ProgressUtils progressUtils = new ProgressUtils();
        // 执行命令
        ExecUtils.exec(command, s -> progressUtils.watchTimeProgress(s, listener));

        if (listener != null) {
            listener.over();
        }
    }
}
