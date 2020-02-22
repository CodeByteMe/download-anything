package top.cyblogs.init;

import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;
import top.cyblogs.data.HttpData;
import top.cyblogs.data.PathData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Windows版FFMpeg下载
 */
@Slf4j
public class DownloadFFMpeg {

    public static void download() {
        try {
            downloadFFMpeg();
        } catch (IOException e) {
            log.error("FFMpeg下载失败, 请检查你的网络...");
        }
    }

    /**
     * 下载FFMpeg程序
     */
    @SuppressWarnings("all")
    private static void downloadFFMpeg() throws IOException {
        if (PathData.FFMPEG.exists()) {
            return;
        }

        URLConnection connection = new URL(HttpData.FFMPEG_URL).openConnection();
        log.info("正在下载FFMpeg, 请稍等, 如果长时间无响应, 请手动重启程序...");
        PathData.FFMPEG.getParentFile().mkdirs();
        ProgressBar pb = new ProgressBar("FFMpeg ==> ", connection.getContentLength(), ProgressBarStyle.ASCII);
        pb.setExtraMessage("下载中...");
        try (InputStream zip = connection.getInputStream()) {
            FileInputStream download = InitializeUtils.download(zip, PathData.FFMPEG_ZIP, pb::stepTo);
            pb.close();
            log.info("正在解压FFMpeg...");
            InitializeUtils.unZip(download, PathData.FFMPEG);
            log.info("删除FFMpeg临时文件...");
            PathData.FFMPEG_ZIP.delete();
        }
    }
}
