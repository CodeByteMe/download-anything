package top.cyblogs.service;

import top.cyblogs.ffmpeg.exec.DownloadM3U8;
import top.cyblogs.ffmpeg.listener.FFMpegListener;
import top.cyblogs.util.FileUtils;

import java.io.File;

public class HlsVideoService {

    public static void download(String url, File targetFile) {
        FileUtils.mkdirs(targetFile);
        DownloadM3U8.exec(url, targetFile, new FFMpegListener() {
            @Override
            public void start() {
                super.start();
            }

            @Override
            public void progress(double current, double total) {
                super.progress(current, total);
            }

            @Override
            public void over() {
                System.out.println("完事了..");
            }
        });
    }
}
