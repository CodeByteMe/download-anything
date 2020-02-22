package top.cyblogs.download.downloader;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.BiliBiliData;
import top.cyblogs.api.VcApi;
import top.cyblogs.data.SettingsData;
import top.cyblogs.service.NormalDownloadService;
import top.cyblogs.util.FileUtils;
import top.cyblogs.utils.BiliBiliUtils;

import java.io.File;

public class VcDownloader {

    public static void download(String url) {
        String playId = BiliBiliUtils.getPlayId(url);
        System.out.println(playId);
        JsonNode playDetail = VcApi.getPlayDetail(playId);
        JsonNode playItem = playDetail.findValue("data").findValue("item");
        String title = FileUtils.getPrettyFileName(playItem.findValue("description").asText());
        String videoPlayUrl = playItem.findValue("video_playurl").asText();
        NormalDownloadService.download(videoPlayUrl, new File(SettingsData.path + title + ".mp4"), BiliBiliData.header);
    }
}
