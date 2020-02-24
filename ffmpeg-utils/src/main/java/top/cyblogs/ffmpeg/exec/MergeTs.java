package top.cyblogs.ffmpeg.exec;

import top.cyblogs.ffmpeg.command.FFMpegCommand;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.ffmpeg.utils.ExecUtils;
import top.cyblogs.ffmpeg.utils.ProgressUtils;
import top.cyblogs.util.FileUtils;

import java.io.File;
import java.util.List;

/**
 * 合并ts文件
 *
 * @author CY  测试通过
 */
public class MergeTs {


    /**
     * 合并多个视频文件到一个视频文件中（这个方法只支持TS或者FLV的合并，所以合并之前一定要先转换为TS或FLV文件）
     * [源文件不会被删除]
     *
     * @param videos   视频文件列表
     * @param out      文件输出位置
     * @param listener 监听器
     */
    public synchronized static void exec(List<File> videos, File out, FFMpegListener listener) {

        // 检查参数
        if (videos == null || videos.size() == 0 || out == null) {
            throw new IllegalArgumentException("合并TS文件时参数异常!");
        }

        if (listener != null) {
            listener.start();
        }

        // 建立目标文件夹
        FileUtils.mkdirs(out);

        // 如果存在就删除
        FileUtils.deleteOnExists(out);

        // 获取命令
        List<String> command = FFMpegCommand.mergeTs(videos, out);

        ProgressUtils progressUtils = new ProgressUtils();
        // 执行命令
        ExecUtils.exec(command, s -> {
            progressUtils.watchTimeProgress(s, listener);
        });

        try {
            // 给一个延时，不要和FFMpeg进程抢资源
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        if (listener != null) {
            listener.over();
        }
    }
}
