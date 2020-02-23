package top.cyblogs.ffmpeg.exec;

import top.cyblogs.ffmpeg.command.FFMpegCommand;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.ffmpeg.utils.ExecUtils;
import top.cyblogs.ffmpeg.utils.ProgressUtils;
import top.cyblogs.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 视频文件转换为ts,支持单个文件转换和批量转换
 *
 * @author CY
 */
public class Convert2Ts {

    /**
     * 将视频文件转换为TS文件
     *
     * @param videos 视频文件列表
     * @return 转换后的文件列表
     */
    public synchronized static ArrayList<File> exec(List<File> videos, FFMpegListener listener) {

        if (videos == null) {
            throw new IllegalArgumentException("批量转换到TS时参数异常!");
        }

        if (listener != null) {
            listener.start();
        }

        // 用来存放已经被转换完成的文件
        ArrayList<File> newFiles = new ArrayList<>();

        AtomicInteger count = new AtomicInteger(0);

        for (File video : videos) {

            File newFile = exec(video, video, new FFMpegListener() {
                @Override
                public void over() {
                    if (listener != null) {
                        listener.progress(count.addAndGet(1), videos.size());
                    }
                }
            });

            newFiles.add(newFile);
        }

        try {
            // 给一个延时，不要和FFMpeg进程抢资源
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        if (listener != null) {
            listener.over();
        }

        return newFiles;
    }

    /**
     * 转换视频文件到TS格式
     * 【支持单文件的转换】
     *
     * @param video    视频文件
     * @param out      输出路径
     * @param listener 监听器
     */
    public synchronized static File exec(File video, File out, FFMpegListener listener) {

        // 检查参数
        if (video == null || out == null) {
            throw new IllegalArgumentException("转换到TS时参数异常!");
        }

        // 替换后缀名为ts
        int lastPoint = out.getAbsolutePath().lastIndexOf(".");
        File newFile = new File(out.getAbsolutePath().substring(0, lastPoint) + ".ts");

        if (listener != null) {
            listener.start();
        }

        // 建立目标文件夹
        FileUtils.mkdirs(newFile);

        FileUtils.deleteOnExists(newFile);

        // 获取命令
        List<String> command = FFMpegCommand.convert2Ts(video, newFile);

        ProgressUtils progressUtils = new ProgressUtils();
        // 执行命令
        ExecUtils.exec(command, s -> progressUtils.watchTimeProgress(s, listener));

        if (listener != null) {
            listener.over();
        }
        return newFile;
    }
}
