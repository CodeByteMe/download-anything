package top.cyblogs.download.downloader;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.BiliBiliData;
import top.cyblogs.api.EpApi;
import top.cyblogs.api.SsApi;
import top.cyblogs.data.SettingsData;
import top.cyblogs.service.SegmentVideoService;
import top.cyblogs.service.SeperateVideoService;
import top.cyblogs.util.FileUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.BiliBiliUtils;

import java.io.File;

public class SsDownloader {

    public static void main(String[] args) {
        download("https://www.bilibili.com/bangumi/play/ss29334/");
    }

    public static void download(String url) {
        String playId = BiliBiliUtils.getPlayId(url);
        JsonNode epInitialState = SsApi.getSsInitialState(playId);
        // 标题
        String title = FileUtils.getPrettyFileName(epInitialState.findValue("mediaInfo").findValue("title").asText());
        // 正片
        epInitialState.findValue("epList").forEach(y -> downloadEp(y, title));

        epInitialState.findValue("sections").forEach(x -> {
            String sectionTitle = FileUtils.getPrettyFileName(x.findValue("title").asText());
            x.findValue("epList").forEach(y -> downloadEp(y,
                    title + File.separator + sectionTitle));
        });
    }

    private static void downloadEp(JsonNode epItem, String parentPath) {

        String title = epItem.findValue("titleFormat").asText() + " " + epItem.findValue("longTitle").asText();

        String badge = epItem.get("badge").asText();
        if (StringUtils.isNotEmpty(badge)) {
            title = "[" + badge + "] " + title;
        }

        String epTitle = parentPath + File.separator + FileUtils.getPrettyFileName(title);

        JsonNode videoUrl = EpApi.getVideoUrl(epItem.get("aid").asText(), epItem.get("cid").asText(), epItem.get("id").asText());

        // 如果视频为dash
        JsonNode dash = videoUrl.findValue("dash");
        if (dash != null) {
            String[] dashUrl = getDashUrl(dash);
            SeperateVideoService.download(dashUrl, new File(SettingsData.path + epTitle + ".mp4"), BiliBiliData.header);
            return;
        }
        // 如果视频为durl
        JsonNode durl = videoUrl.findValue("durl");
        if (durl != null) {
            String[] durlUrl = getDurlUrl(durl);
            SegmentVideoService.download(durlUrl, new File(SettingsData.path + epTitle + ".mp4"), BiliBiliData.header);
        }
    }

    /**
     * 下载Dash视频
     */
    private static String[] getDashUrl(JsonNode dash) {
        String videoUrl = dash.findValue("video").get(0).findValue("baseUrl").asText();
        String audioUrl = dash.findValue("audio").get(0).findValue("baseUrl").asText();
        return new String[]{videoUrl, audioUrl};
    }

    /**
     * 下载Durl视频
     */
    private static String[] getDurlUrl(JsonNode durl) {
        String[] flvUrls = new String[durl.size()];
        for (int i = 0; i < durl.size(); i++) {
            flvUrls[i] = durl.get(i).findValue("url").asText();
        }
        return flvUrls;
    }
}
