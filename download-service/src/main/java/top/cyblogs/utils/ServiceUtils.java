package top.cyblogs.utils;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * 工具
 */
public class ServiceUtils {

    /**
     * 等待完成下载
     * Aria2给出下载结束指令的时候，其实并没有下载完成，还在进行最后的处理，也就是aria2后缀文件还存在
     */
    public static void waitingDownloadFinish(List<File> files, Consumer<List<File>> consumer) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    // 如果Aria2文件都消失了，说明真正的下载完了
                    if (files.stream().noneMatch(x -> new File(x.getAbsolutePath() + ".aria2").exists())) {
                        break;
                    }
                }
                if (consumer != null) consumer.accept(files);
            } catch (InterruptedException ignored) {
            }
        });
        executorService.shutdown();
    }
}
