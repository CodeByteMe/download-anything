package top.cyblogs.download.downloader;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.BiliBiliData;
import top.cyblogs.api.AmApi;
import top.cyblogs.api.AuApi;
import top.cyblogs.data.SettingsData;
import top.cyblogs.service.NormalDownloadService;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.BiliBiliUtils;

import java.io.File;

public class AmDownloader {

    public static void download(String url) {

        String playId = BiliBiliUtils.getPlayId(url);

        System.err.println(playId);

        JsonNode albumInfo = AmApi.getAlbumInfo(playId);
        String albumTitle = albumInfo.findValue("title").asText();
        JsonNode albumList = AmApi.getAlbumList(playId);

        albumList.findValue("data").findValue("data").forEach(x -> {
            String author = x.findValue("author").asText();
            String title = x.findValue("title").asText();
            String lyric = x.findValue("lyric").asText();


            String filePath = FileUtils.getPrettyFileName(albumTitle) + File.separator
                    + FileUtils.getPrettyFileName(author + (StringUtils.isNotEmpty(author) ? " - " : "") + title);

            JsonNode musicUrl = AuApi.getMusicUrl(x.findValue("id").asText());
            String music = musicUrl.findValue("data").findValue("cdns").get(0).asText();

            NormalDownloadService.download(music, new File(SettingsData.path + filePath + ".mp3"), BiliBiliData.header);

            if (StringUtils.isNotEmpty(lyric)) {
                NormalDownloadService.download(lyric, new File(SettingsData.path + filePath + ".lrc"), BiliBiliData.header);
            }
            long aid = x.findValue("aid").asLong();
            if (aid > 0) {
                AvDownloader.download("https://www.bilibili.com/video/av" + aid);
            }
        });
    }
}
