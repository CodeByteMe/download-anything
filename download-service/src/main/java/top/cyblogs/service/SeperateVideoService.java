package top.cyblogs.service;

import lombok.extern.slf4j.Slf4j;
import top.cyblogs.data.PathData;
import top.cyblogs.download.DownloadListener;
import top.cyblogs.download.DownloadUtils;
import top.cyblogs.ffmpeg.exec.MergeVideoAndAudio;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.output.Aria2cStatus;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.ServiceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static void download(String[] seperateUrl, File targetFile, Map<String, String> header) {
        if (targetFile.exists()) return;
        // 下载完成数量计数
        AtomicInteger finishCount = new AtomicInteger(0);
        // 临时文件列表
        List<File> tempFiles = new ArrayList<>();

        for (String url : seperateUrl) {

            String noQueryUrl = url.split("\\?")[0];

            File tempFile = new File(PathData.TEMP_FILE_PATH + StringUtils.md5(noQueryUrl) + ".m4s");

            FileUtils.mkdirs(tempFile);

            tempFiles.add(tempFile);

            DownloadUtils.download(url, tempFile, header, new DownloadListener() {

                @Override
                public void active(Aria2cStatus status) {

                }

                @Override
                public void complete(Aria2cStatus status) {
                    if (seperateUrl.length == finishCount.addAndGet(1)) {
                        mergeVideo(tempFiles, targetFile);
                    }
                }
            });
        }
    }

    /**
     * 合并视频
     *
     * @param segmentFiles 片段文件
     * @param targetFile   目标文件
     */
    private static void mergeVideo(List<File> segmentFiles, File targetFile) {
        ServiceUtils.waitingDownloadFinish(segmentFiles, files -> {
            log.info("正在合并音视频...{}", targetFile.getName());
            MergeVideoAndAudio.exec(files.get(0), files.get(1), targetFile, new FFMpegListener() {
                @Override
                public void over() {
                    // 合并完成后删除下载的临时文件
                    segmentFiles.forEach(FileUtils::deleteOnExists);
                }
            });
        });
    }
}
