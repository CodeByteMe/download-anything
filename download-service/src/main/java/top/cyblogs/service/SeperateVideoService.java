package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.ffmpeg.exec.MergeVideoAndAudio;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.util.FileUtils;
import top.cyblogs.utils.ServiceUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 音视频分开的文件下载服务
 *
 * @author CY
 */
@Slf4j
public class SeperateVideoService {

    /**
     * 下载分段视频并合并
     *
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    public static void download(String[] seperateUrls, File targetFile, Map<String, String> header, DownloadItem downloadStatus) {
        new TempDownloadService().download(seperateUrls, targetFile, header, downloadStatus, SeperateVideoService::mergeVideo);
    }

    /**
     * 合并视频
     *
     * @param seperateUrls 片段文件
     * @param targetFile   目标文件
     */
    private static void mergeVideo(List<File> seperateUrls, File targetFile, DownloadItem downloadStatus) {
        ServiceUtils.waitingDownloadFinish(seperateUrls, files -> {
            MergeVideoAndAudio.exec(files.get(0), files.get(1), targetFile, new FFMpegListener() {
                @Override
                public void start() {
                    downloadStatus.setStatusFormat("正在合并...");
                    downloadStatus.setStatus(DownloadStatus.MERGING);
                    downloadStatus.setProgress(0D);
                    downloadStatus.setProgressFormat("0%");
                }

                @Override
                public void progress(long current, long total) {
                    downloadStatus.setProgress((double) current / total * 100);
                    downloadStatus.setProgressFormat(ServiceUtils.ratioString(current, total, true));
                }

                @Override
                public void over() {
                    downloadStatus.setStatusFormat("合并完成!");
                    downloadStatus.setStatus(DownloadStatus.FINISHED);
                    downloadStatus.setProgress(100D);
                    downloadStatus.setProgressFormat("100%");
                    // 合并完成后删除下载的临时文件
                    seperateUrls.forEach(FileUtils::deleteOnExists);
                }
            });
        });
    }
}
