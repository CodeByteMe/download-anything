package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.BaseData;
import top.cyblogs.data.SettingsData;
import top.cyblogs.download.BaseDownloadListener;
import top.cyblogs.download.DownloadUtils;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.output.Aria2cStatus;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.ServiceUtils;

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
        new NormalDownloadService().execDownload(url, targetFile, header, downloadStatus);
    }

    /**
     * 下载分段视频并合并
     *
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    private void execDownload(String url, File targetFile, Map<String, String> header, DownloadItem downloadStatus) {

        downloadStatus.setDownloadId(StringUtils.md5(targetFile.getAbsolutePath()));
        String name = targetFile.getName();
        downloadStatus.setFileName(name.substring(0, name.lastIndexOf(".")));
        downloadStatus.setTargetPath(targetFile.getAbsolutePath());
        downloadStatus.setStatus(DownloadStatus.WAITING);
        downloadStatus.setStatusFormat("等待下载...");
        downloadStatus.setProgressFormat("0%");
        downloadStatus.setProgress(0D);
        ServiceUtils.addToList(downloadStatus);

        if (SettingsData.skipIfExists && targetFile.exists()) {
            downloadStatus.setStatusFormat("文件已存在!");
            downloadStatus.setStatus(DownloadStatus.FINISHED);
            downloadStatus.setCurrentSpeed(null);
            downloadStatus.setTotalSize(FileUtils.fileLength(targetFile.length()));
            downloadStatus.setProgressFormat("100%");
            downloadStatus.setProgress(100D);
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
                downloadStatus.setTotalSize(FileUtils.fileLength(status.getTotalLength()));
                downloadStatus.setProgress((double) status.getCompletedLength() / status.getTotalLength() * 100);
                downloadStatus.setProgressFormat(ServiceUtils.ratioString(status.getCompletedLength(), status.getTotalLength(), true));
            }

            @Override
            public void paused(Aria2cStatus status) {
                super.paused(status);
            }

            // TODO 需要测试
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
                downloadStatus.setProgress(100D);
                downloadStatus.setProgressFormat("100%");
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
