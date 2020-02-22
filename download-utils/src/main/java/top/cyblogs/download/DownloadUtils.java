package top.cyblogs.download;

import top.cyblogs.Aria2c;
import top.cyblogs.data.PathData;
import top.cyblogs.input.Aria2cOptions;
import top.cyblogs.output.Aria2cStatus;
import top.cyblogs.start.Aria2cRpcOptions;
import top.cyblogs.support.DownloadTaskStatus;
import top.cyblogs.support.Options;
import top.cyblogs.support.Secret;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 模拟下载，暂时还没有对接Aria2c
 */
public class DownloadUtils {

    /**
     * 下载状态观察
     */
    private static StatusObserver observer = new StatusObserver();

    /**
     * Aria2c工具
     */
    private static Aria2c aria2c;

    /**
     * 连接Aria2c的密钥
     */
    private static String token;

    static {
        // Aria2的启动选项
        List<String> startOptions = new ArrayList<>();
        // 默认的下载目录
        startOptions.add("--dir=" + PathData.TEMP_FILE_PATH);
        // 检查文件的完整性
        startOptions.add("--check-integrity=true");
        // continue=true
        startOptions.add("--continue=true");
        // 单个任务最大线程数
        startOptions.add("--split=20");
        // 最大同时下载任务数
        startOptions.add("--max-concurrent-downloads=20");
        // 启动
        aria2c = Aria2c.run(startOptions);
        // 密钥
        token = Secret.token(Aria2cRpcOptions.getInstance().getRpcSecret());
        // 做任务监听，每秒钟发送一次请求
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            ArrayList<Aria2cStatus> status = new ArrayList<>();
            status.addAll(Arrays.asList(aria2c.tellActive(token, new String[]{})));
            status.addAll(Arrays.asList(aria2c.tellWaiting(token, 0, Integer.MAX_VALUE, new String[]{})));
            status.addAll(Arrays.asList(aria2c.tellStopped(token, 0, Integer.MAX_VALUE, new String[]{})));
            observer.setStatusList(status);
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 下载一个链接
     *
     * @param header           请求头
     * @param downloadListener 监听器
     */
    public static void download(String url, File out, Map<String, String> header, DownloadListener downloadListener) {

        Aria2cOptions options = Aria2cOptions.builder()
                .header(getHeader(header))
                // 输出目录
                .dir(out.getParentFile().getAbsolutePath())
                // 输出文件
                .out(out.getName())
                .build();

        // 开始下载
        String gid = aria2c.addUri(token, new String[]{url},
                Options.of(options), Integer.MAX_VALUE);

        // 下载回调
        callBack(downloadListener, gid);
    }

    /**
     * 下载回调
     */
    @SuppressWarnings("unchecked")
    private static void callBack(DownloadListener downloadListener, String gid) {
        observer.getPropertyChangeSupport().addPropertyChangeListener(event -> {
            List<Aria2cStatus> statuses = (List<Aria2cStatus>) event.getNewValue();
            Aria2cStatus status = statuses.stream().filter(x -> gid.equals(x.getGid())).findFirst().orElse(null);
            if (downloadListener != null && status != null) {
                switch (status.getStatus()) {
                    case DownloadTaskStatus.ACTIVE: {
                        downloadListener.active(status);
                        break;
                    }
                    case DownloadTaskStatus.COMPLETE: {
                        downloadListener.complete(status);
                        break;
                    }
                    case DownloadTaskStatus.ERROR: {
                        downloadListener.error(status);
                        break;
                    }
                    case DownloadTaskStatus.PAUSED: {
                        downloadListener.paused(status);
                        break;
                    }
                    case DownloadTaskStatus.REMOVED: {
                        downloadListener.removed(status);
                        break;
                    }
                    case DownloadTaskStatus.USED: {
                        downloadListener.used(status);
                        break;
                    }
                    case DownloadTaskStatus.WAITING: {
                        downloadListener.waiting(status);
                        break;
                    }
                }
            }
        });
    }

    /**
     * 获取Aria2适用的Header
     */
    private static String[] getHeader(Map<String, String> header) {
        if (header == null) return new String[0];
        List<String> headers = new ArrayList<>();
        header.forEach((key, value) -> headers.add(String.format("%s: %s", key, value)));
        return headers.toArray(String[]::new);
    }
}
