package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.BaseData;
import top.cyblogs.data.PathData;
import top.cyblogs.data.SettingsData;
import top.cyblogs.download.BaseDownloadListener;
import top.cyblogs.download.DownloadUtils;
import top.cyblogs.listener.MergeVideoListener;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.output.Aria2cStatus;
import top.cyblogs.support.DownloadTaskStatus;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.ServiceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 下载临时文件服务, 仅在当前模块使用
 * <p>
 * @author CY
 */
@Slf4j
class TempDownloadService {

    private Integer currentRetryCount = 0;

    /**
     * 下载视频集合并合并
     *
     * @param urls       视频集合URL
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    void download(String[] urls, File targetFile, Map<String, String> header,
                  DownloadItem downloadStatus, MergeVideoListener listener) {

        String name = targetFile.getName();
        downloadStatus.setFileName(name.substring(0, name.lastIndexOf(".")));
        downloadStatus.setTargetPath(targetFile.getAbsolutePath());
        downloadStatus.setStatus(DownloadStatus.WAITING);
        downloadStatus.setStatusFormat("等待下载...");
        downloadStatus.setProgressFormat("0%");
        downloadStatus.setProgress(0D);
        downloadStatus.setDownloadId(StringUtils.md5(targetFile.getAbsolutePath()));
        ServiceUtils.addToList(downloadStatus);

        // 跳过已经存在
        if (SettingsData.skipIfExists && targetFile.exists()) {
            downloadStatus.setStatusFormat("文件已存在!");
            downloadStatus.setStatus(DownloadStatus.FINISHED);
            downloadStatus.setCurrentSpeed(null);
            downloadStatus.setTotalSize(FileUtils.fileLength(targetFile.length()));
            downloadStatus.setProgressFormat("100%");
            downloadStatus.setProgress(100.0);
            return;
        }

        // 临时文件列表
        List<File> tempFiles = new ArrayList<>();

        // 下载列表的状态
        ArrayList<Aria2cStatus> listStatus = new ArrayList<>(urls.length);

        // 因为下面循环中的监听器会被new出来两次，所以将合并视频的flag变量放在这里
        AtomicBoolean isMerged = new AtomicBoolean(false);

        for (int i = 0; i < urls.length; i++) {

            File tempFile = new File(PathData.TEMP_FILE_PATH
                    + StringUtils.md5(urls[i].split("\\?")[0]) + ".m4s");

            FileUtils.mkdirs(tempFile);

            tempFiles.add(tempFile);

            // 初始化一个状态
            listStatus.add(new Aria2cStatus());

            int index = i;

            DownloadUtils.download(urls[index], tempFile, header, new BaseDownloadListener() {

                @Override
                public void paused(Aria2cStatus status) {
                    super.paused(status);
                }

                // TODO 需要测试
                @Override
                public void error(Aria2cStatus status) {

                    if (++currentRetryCount <= BaseData.RETRY_COUNT) {
                        log.info("正在重试下载...");
                        downloadStatus.setStatusFormat("重试下载...");
                        DownloadUtils.download(urls[index], targetFile, header, this);
                    }
                }

                @Override
                public void removed(Aria2cStatus status) {
                    super.removed(status);
                }

                @Override
                public void used(Aria2cStatus status) {
                    super.used(status);
                }

                @Override
                public void active(Aria2cStatus status) {
                    listStatus.set(index, status);
                    if (listStatus.stream().anyMatch(x -> DownloadTaskStatus.ACTIVE.equals(x.getStatus()))) {
                        // 当前下载尺寸
                        AtomicLong current = new AtomicLong(0);
                        // 总尺寸
                        AtomicLong total = new AtomicLong(0);
                        // 下载速度
                        AtomicLong downloadSpeed = new AtomicLong(0);

                        listStatus.forEach(x -> {
                            current.addAndGet(x.getCompletedLength());
                            total.addAndGet(x.getTotalLength());
                            downloadSpeed.addAndGet(x.getDownloadSpeed());
                        });

                        downloadStatus.setStatusFormat("正在下载...");
                        downloadStatus.setStatus(DownloadStatus.DOWNLOADING);
                        downloadStatus.setProgress((double) current.get() / total.get() * 100);
                        downloadStatus.setProgressFormat(ServiceUtils.ratioString(current.get(), total.get(), true));
                        downloadStatus.setCurrentSpeed(FileUtils.fileLength(downloadSpeed.get()) + "/S");
                        downloadStatus.setTotalSize(FileUtils.fileLength(total.get()));
                    }
                }

                @Override
                public synchronized void complete(Aria2cStatus status) {
                    listStatus.set(index, status);
                    // 加个锁防止执行太快导致下面的合并执行了两次
                    synchronized (TempDownloadService.class) {
                        if (!isMerged.get() && listStatus.stream().allMatch(x -> DownloadTaskStatus.COMPLETE.equals(x.getStatus()))) {
                            isMerged.set(true);
                            // 下载完成
                            downloadStatus.setCurrentSpeed(null);
                            downloadStatus.setProgressFormat("100%");
                            downloadStatus.setProgress(100.0);
                            downloadStatus.setStatusFormat("等待合并...");
                            if (listener != null) {
                                listener.startMerge(tempFiles, targetFile, downloadStatus);
                            }
                        }
                    }
                }
            });
        }
    }
}
