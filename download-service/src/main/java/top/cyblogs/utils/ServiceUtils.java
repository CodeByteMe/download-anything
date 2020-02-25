package top.cyblogs.utils;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.DownloadList;
import top.cyblogs.exception.AlreadyExistsException;
import top.cyblogs.model.DownloadItem;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * 工具
 *
 * @author CY 测试通过
 */
@Slf4j
public class ServiceUtils {

    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("0.00");

    /**
     * 格式化进度信息
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
        // 判断是否已经完成了，防止因为关闭线程不及时造成的多次合并问题
        AtomicBoolean isFinished = new AtomicBoolean(false);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            // 当前状态是还没有完成，并且Aria2文件都消失了，说明真正的下载完了
            if (!isFinished.get() && files.stream().noneMatch(x -> new File(x.getAbsolutePath() + ".aria2").exists())) {
                isFinished.set(true);
                if (consumer != null) {
                    consumer.accept(files);
                }
                scheduledThreadPool.shutdownNow();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 添加下载状态到列表中
     *
     * @param downloadStatus 下载状态
     */
    public static void addToList(DownloadItem downloadStatus) {
        if (DownloadList.list.stream().anyMatch(x -> x.getDownloadId().equals(downloadStatus.getDownloadId()))) {
            throw new AlreadyExistsException("重复添加了...");
        }
        DownloadList.list.add(downloadStatus);
    }
}
