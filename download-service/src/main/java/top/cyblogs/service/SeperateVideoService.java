package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.DownloadList;
import top.cyblogs.ffmpeg.exec.MergeVideoAndAudio;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.util.FileUtils;
import top.cyblogs.utils.ServiceUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 音视频分开的文件下载服务
 */
@Slf4j
public class SeperateVideoService {

    /**
     * 下载分段视频并合并
     *
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    public static void download(String[] seperateUrl, File targetFile, Map<String, String> header, DownloadItem downloadStatus) {
        DownloadList.list.add(downloadStatus);
        new TempDownloadService().download(seperateUrl, targetFile, header, downloadStatus, SeperateVideoService::mergeVideo);
    }

    /**
     * 合并视频
     *
     * @param segmentFiles 片段文件
     * @param targetFile   目标文件
     */
    private static void mergeVideo(List<File> segmentFiles, File targetFile, DownloadItem initialItem) {
        ServiceUtils.waitingDownloadFinish(segmentFiles, files -> {
            log.info("开始合并...");
            MergeVideoAndAudio.exec(files.get(0), files.get(1), targetFile, new FFMpegListener() {

                @Override
                public void start() {
                    super.start();
                }

                @Override
                public void progress(long current, long total) {
                    super.progress(current, total);
                }

                @Override
                public void over() {
                    // 合并完成后删除下载的临时文件
                    segmentFiles.forEach(FileUtils::deleteOnExists);
                }
            });
        });
    }
}
