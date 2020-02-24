package top.cyblogs.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 工具
 *
 * @author CY
 */
@Slf4j
public class ServiceUtils {

    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("0.00");

    /**
     * 比例
     */
    public static String ratioString(long current, long total, boolean isPercent) {
        if (total == 0) {
            return isPercent ? "?%" : "-/-";
        }
        return isPercent ? PERCENT_FORMAT.format((double) current / total * 100) + "%" : current + "/" + total;
    }

    /**
     * 等待完成下载
     * Aria2给出下载结束指令的时候，其实并没有下载完成，还在进行最后的处理，也就是aria2后缀文件还存在
     */
    public static void waitingDownloadFinish(List<File> files, Consumer<List<File>> consumer) {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            // 如果Aria2文件都消失了，说明真正的下载完了
            if (files.stream().noneMatch(x -> new File(x.getAbsolutePath() + ".aria2").exists())) {
                if (consumer != null) {
                    log.info("开始合并...");
                    consumer.accept(files);
                }
                scheduledThreadPool.shutdownNow();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
