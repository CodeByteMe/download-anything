package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.DownloadList;
import top.cyblogs.data.SettingsData;
import top.cyblogs.ffmpeg.exec.DownloadM3U8;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.model.enums.DownloadType;
import top.cyblogs.util.FileUtils;
import top.cyblogs.utils.ServiceUtils;

import java.io.File;

/**
 * M3U8文件下载服务
 *
 * @author CY
 */
@Slf4j
public class HlsVideoService {

    public static void download(String url, File targetFile, DownloadItem downloadStatus) {

        DownloadList.list.add(downloadStatus);

        // 文件存在就跳过
        if (SettingsData.skipIfExists && targetFile.exists()) {
            return;
        }

        FileUtils.mkdirs(targetFile);

        DownloadM3U8.exec(url, targetFile, new FFMpegListener() {

            @Override
            public void start() {
                // 开始下载
                downloadStatus.setStatusFormat("已连接...");
                downloadStatus.setStatus(DownloadStatus.DOWNLOADING);
                downloadStatus.setCurrentSpeed("-/S");
                downloadStatus.setDownloadType(DownloadType.VIDEO);
                String name = targetFile.getName();
                downloadStatus.setFileName(name.substring(0, name.lastIndexOf(".")));
                downloadStatus.setTotalSize("未知大小");
                downloadStatus.setTargetPath(targetFile.getAbsolutePath());
            }

            @Override
            public void progress(long current, long total) {
                // 下载进度
                downloadStatus.setStatusFormat("正在下载...");
                downloadStatus.setProgressFormat(ServiceUtils.ratioString(current, total, false));
            }

            @Override
            public void over() {
                // 下载完成
                downloadStatus.setStatusFormat("下载完成!");
                downloadStatus.setStatus(DownloadStatus.FINISHED);
                downloadStatus.setCurrentSpeed(null);
            }
        });
    }
}
