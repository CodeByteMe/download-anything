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
import java.util.concurrent.atomic.AtomicLong;

/**
 * 下载临时文件服务, 仅在当前模块使用
 * <p>
 * TODO 这个类有严重的问题
 *
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

        // 跳过已经存在
        if (SettingsData.skipIfExists && targetFile.exists()) {
            return;
        }

        String name = targetFile.getName();
        downloadStatus.setFileName(name.substring(0, name.lastIndexOf(".")));
        downloadStatus.setTargetPath(targetFile.getAbsolutePath());

        // 临时文件列表
        List<File> tempFiles = new ArrayList<>();

        // 下载列表的状态
        ArrayList<Aria2cStatus> listStatus = new ArrayList<>(urls.length);

        for (int i = 0; i < urls.length; i++) {

            File tempFile = new File(PathData.TEMP_FILE_PATH
                    + StringUtils.md5(urls[i].split("\\?")[0]) + ".flv");

            FileUtils.mkdirs(tempFile);

            tempFiles.add(tempFile);

            // 初始化一个状态
            listStatus.add(new Aria2cStatus());

            int index = i;
            DownloadUtils.download(urls[index], tempFile, header, new BaseDownloadListener() {

                // TODO 需要测试
                @Override
                public void waiting(Aria2cStatus status) {
                    listStatus.set(index, status);
                    System.out.println(listStatus.size());
                    if (listStatus.stream().allMatch(x -> DownloadTaskStatus.WAITING.equals(x.getStatus()))) {
                        log.info("等待中...");
                        downloadStatus.setStatus(DownloadStatus.WAITING);
                        downloadStatus.setStatusFormat("等待下载...");
                    }
                }

                @Override
                public void paused(Aria2cStatus status) {
                    super.paused(status);
                }

                @Override
                public void error(Aria2cStatus status) {
                    downloadStatus.setStatusFormat("重试下载...");
                    downloadStatus.setProgressFormat(null);
                    if (++currentRetryCount <= BaseData.RETRY_COUNT) {
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
                        AtomicLong current = new AtomicLong(0);
                        AtomicLong total = new AtomicLong(0);
                        AtomicLong downloadSpeed = new AtomicLong(0);

                        listStatus.forEach(x -> {
                            downloadSpeed.addAndGet(x.getDownloadSpeed());
                            total.addAndGet(x.getTotalLength());
                            current.addAndGet(x.getCompletedLength());
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
                public void complete(Aria2cStatus status) {
                    listStatus.set(index, status);
                    System.out.println(listStatus.size());
                    if (listStatus.stream().allMatch(x -> DownloadTaskStatus.COMPLETE.equals(x.getStatus()))) {
                        // 下载完成
                        downloadStatus.setStatusFormat("下载完成!");
                        downloadStatus.setStatus(DownloadStatus.FINISHED);
                        downloadStatus.setCurrentSpeed(null);
                        downloadStatus.setProgressFormat("100%");
                        downloadStatus.setProgress(100.0);
                        if (listener != null) {
                            listener.startMerge(tempFiles, targetFile, downloadStatus);
                        }
                    }
                }
            });
        }
    }
}
