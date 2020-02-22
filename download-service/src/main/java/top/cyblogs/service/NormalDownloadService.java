package top.cyblogs.service;

import top.cyblogs.download.DownloadListener;
import top.cyblogs.download.DownloadUtils;
import top.cyblogs.output.Aria2cStatus;
import top.cyblogs.util.FileUtils;

import java.io.File;
import java.util.Map;

public class NormalDownloadService {

    /**
     * 下载分段视频并合并
     *
     * @param targetFile 下载到目标位置
     * @param header     下载所需的请求头
     */
    public static void download(String url, File targetFile, Map<String, String> header) {

        FileUtils.mkdirs(targetFile);

        DownloadUtils.download(url, targetFile, header, new DownloadListener() {
            @Override
            public void active(Aria2cStatus status) {

            }

            @Override
            public void complete(Aria2cStatus status) {

            }
        });

    }
}
