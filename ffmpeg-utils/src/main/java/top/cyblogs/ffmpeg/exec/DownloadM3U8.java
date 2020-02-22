package top.cyblogs.ffmpeg.exec;

import top.cyblogs.ffmpeg.command.FFMpegCommand;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载M3U8
 */
public class DownloadM3U8 {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);


    /**
     * 解析M3U8下载视频
     * 【不会产生临时文件】
     *
     * @param m3u8URL  M3U8的URL可以是File
     * @param out      输出的路径
     * @param listener 监听器
     */
    public static void exec(String m3u8URL, File out, FFMpegListener listener) {
        executor.submit(() -> download(m3u8URL, out, listener));
    }

    private static void download(String m3u8URL, File out, FFMpegListener listener) {
        // 判断参数是否正确
        if (!StringUtils.isNotEmpty(m3u8URL) || out == null) {
            throw new IllegalArgumentException("下载M3U8时参数异常!");
        }

        if (listener != null) {
            listener.start();
        }

        // 先删除后下载
        FileUtils.deleteOnExists(out);

        // 建立目标文件夹
        FileUtils.mkdirs(out);

        // 获取命令
        List<String> command = FFMpegCommand.downloadM3U8(m3u8URL, out);

        // 执行命令
        ExecUtils.exec(command, s -> {
            // TODO 命令执行进度
        });

        if (listener != null) listener.over();
    }
}
