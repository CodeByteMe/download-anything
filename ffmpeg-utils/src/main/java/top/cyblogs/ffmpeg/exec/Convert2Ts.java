package top.cyblogs.ffmpeg.exec;

import top.cyblogs.ffmpeg.command.FFMpegCommand;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 视频文件转换为ts,支持单个文件转换和批量转换
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

        ArrayList<File> newFiles = new ArrayList<>(); // 用来存放已经被转换完成的文件

        AtomicInteger count = new AtomicInteger(0);

        for (File video : videos) {
            // 替换后缀名为ts
            int lastPoint = video.getName().lastIndexOf(".");
            File newFile = new File(video.getAbsolutePath().substring(0, lastPoint) + ".ts");

            newFiles.add(newFile);

            FileUtils.deleteOnExists(newFile);

            exec(video, newFile, new FFMpegListener() {
                @Override
                public void over() {
                    if (listener != null) {
                        listener.progress(count.addAndGet(1), videos.size());
                    }
                }
            });
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
    public synchronized static void exec(File video, File out, FFMpegListener listener) {

        // 检查参数
        if (video == null || out == null) {
            throw new IllegalArgumentException("转换到TS时参数异常!");
        }

        if (listener != null) {
            listener.start();
        }

        // 先删除后下载
        FileUtils.deleteOnExists(out);

        // 建立目标文件夹
        FileUtils.mkdirs(out);

        // 获取命令
        List<String> command = FFMpegCommand.convert2TS(video, out);

        // 执行命令
        ExecUtils.exec(command, s -> {
            // TODO 命令执行进度
        });

        if (listener != null) listener.over();
    }
}
