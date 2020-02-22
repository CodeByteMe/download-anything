package top.cyblogs.init;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;
import top.cyblogs.data.HttpData;
import top.cyblogs.data.PathData;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Windows版Aria2c下载
 *
 * @author CY
 */
@Slf4j
public class DownloadAria2c {

    /**
     * JSON中下载地址字段名
     */
    private static final String BROWSER_DOWNLOAD_URL = "browser_download_url";

    public static void download() {
        try {
            downloadAria2c();
        } catch (IOException e) {
            log.error("Aria2c下载失败, 请检查你的网络...");
        }
    }

    /**
     * 下载Aria2c程序
     */
    @SuppressWarnings("all")
    private static void downloadAria2c() throws IOException {
        if (PathData.ARIA2C.exists()) {
            return;
        }

        log.info("正在获取Aria2c的下载地址...");
        String win64Url = getWin64Url();
        log.info("Aria2c的下载地址: {}", win64Url);
        URLConnection connection = new URL(win64Url).openConnection();
        log.info("正在下载Aria2c, 请稍等, 如果长时间无响应, 请手动重启程序...");
        PathData.ARIA2C.getParentFile().mkdirs();
        ProgressBar pb = new ProgressBar("Aria2c ==> ", connection.getContentLength(), ProgressBarStyle.ASCII);
        pb.setExtraMessage("下载中...");
        try (InputStream zip = connection.getInputStream()) {
            FileInputStream download = InitializeUtils.download(zip, PathData.ARIA2C_ZIP, pb::stepTo);
            pb.close();
            log.info("正在解压Aria2c...");
            InitializeUtils.unZip(download, PathData.ARIA2C);
            log.info("删除Aria2c临时文件...");
            PathData.ARIA2C_ZIP.delete();
        }
    }

    /**
     * 获取Windows64位的下载地址
     *
     * @return Windows64位的下载地址
     * @throws IOException *
     */
    private static String getWin64Url() throws IOException {
        InputStream inputStream = new URL(HttpData.ARIA2C_RELEASE_URL).openConnection().getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        JsonNode jsonNode = new ObjectMapper().readTree(bufferedReader);
        List<JsonNode> browserDownloadUrl = jsonNode.findValues(BROWSER_DOWNLOAD_URL);
        return browserDownloadUrl.stream().filter(x -> x != null && x.asText().contains("win-64bit"))
                .findFirst().map(JsonNode::asText).orElse(null);
    }
}
