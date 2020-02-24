package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.DownloadList;
import top.cyblogs.ffmpeg.exec.MergeVideo;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.model.DownloadItem;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.util.FileUtils;
import top.cyblogs.utils.ServiceUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 分段视频下载服务
 *
 * @author CY
 */
@Slf4j
public class SegmentVideoService {

    /**
     * 下载分段视频并合并
     *
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    public static void download(String[] seperateUrl, File targetFile, Map<String, String> header, DownloadItem downloadStatus) {
        DownloadList.list.add(downloadStatus);
        new TempDownloadService().download(seperateUrl, targetFile, header, downloadStatus, SegmentVideoService::mergeVideo);
    }

    /**
     * 合并视频
     *
     * @param segmentFiles 片段文件
     * @param targetFile   目标文件
     */
    private static void mergeVideo(List<File> segmentFiles, File targetFile, DownloadItem downloadStatus) {
        log.info("开始合并...");
        ServiceUtils.waitingDownloadFinish(segmentFiles, files -> {
            MergeVideo.exec(files, targetFile, new FFMpegListener() {

                @Override
                public void start() {
                    downloadStatus.setStatusFormat("正在合并...");
                    downloadStatus.setStatus(DownloadStatus.MERGE);
                }

                @Override
                public void progress(long current, long total) {
                    downloadStatus.setProgress((double) current / total * 100);
                    downloadStatus.setProgressFormat(ServiceUtils.ratioString(current, total, true));
                }

                @Override
                public void over() {
                    downloadStatus.setStatusFormat("合并完成!");
                    // 合并完成后删除下载的临时文件
                    segmentFiles.forEach(FileUtils::deleteOnExists);
                }
            });
        });
    }
}
