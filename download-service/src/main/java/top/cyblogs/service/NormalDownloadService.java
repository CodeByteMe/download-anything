package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.BaseData;
import top.cyblogs.data.DownloadList;
import top.cyblogs.data.SettingsData;
import top.cyblogs.download.BaseDownloadListener;
import top.cyblogs.download.DownloadUtils;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.output.Aria2cStatus;
import top.cyblogs.util.FileUtils;

import java.io.File;
import java.util.Map;

/**
 * 一般下载服务，主要用来下载无需事后处理的单文件，含大文件和小文件
 *
 * @author CY
 */
@Slf4j
public class NormalDownloadService {

    private Integer currentRetryCount = 0;

    public static void download(String url, File targetFile, Map<String, String> header, DownloadItem downloadStatus) {
        DownloadList.list.add(downloadStatus);
        new NormalDownloadService().execDownload(url, targetFile, header, downloadStatus);
    }

    /**
     * 下载分段视频并合并
     *
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    private void execDownload(String url, File targetFile, Map<String, String> header, DownloadItem downloadStatus) {
        if (SettingsData.skipIfExists && targetFile.exists()) {
            return;
        }

        FileUtils.mkdirs(targetFile);

        DownloadUtils.download(url, targetFile, header, new BaseDownloadListener() {
            @Override
            public void active(Aria2cStatus status) {
                // 开始下载
                downloadStatus.setStatusFormat("正在下载...");
                downloadStatus.setStatus(DownloadStatus.DOWNLOADING);
                downloadStatus.setCurrentSpeed(FileUtils.fileLength(status.getDownloadSpeed()) + "/S");
                String name = targetFile.getName();
                downloadStatus.setFileName(name.substring(0, name.lastIndexOf(".")));
                downloadStatus.setTotalSize(FileUtils.fileLength(status.getTotalLength()));
                downloadStatus.setTargetPath(targetFile.getAbsolutePath());
            }

            @Override
            public void waiting(Aria2cStatus status) {
                downloadStatus.setStatus(DownloadStatus.WAITING);
                downloadStatus.setStatusFormat("等待下载...");
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
                    DownloadUtils.download(url, targetFile, header, this);
                }
            }

            @Override
            public void complete(Aria2cStatus status) {
                downloadStatus.setStatusFormat("下载完成!");
                downloadStatus.setStatus(DownloadStatus.FINISHED);
                downloadStatus.setCurrentSpeed(null);
            }

            @Override
            public void removed(Aria2cStatus status) {
                super.removed(status);
            }

            @Override
            public void used(Aria2cStatus status) {
                super.used(status);
            }
        });

    }
}
