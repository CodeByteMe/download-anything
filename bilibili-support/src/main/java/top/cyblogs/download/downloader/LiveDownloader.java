package top.cyblogs.download.downloader;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.BiliBiliData;
import top.cyblogs.api.LiveApi;
import top.cyblogs.data.SettingsData;
import top.cyblogs.service.NormalDownloadService;
import top.cyblogs.util.FileUtils;
import top.cyblogs.utils.BiliBiliUtils;

import java.io.File;

/**
 * 直播下载器
 * <p>
 * 当live_status = 1的时候是直播
 * 当live_status = 2的时候是轮播
 * 当live_status = 0的时候是主播不在
 * <p>
 * 并不支持轮播，因为有些轮播需要实时的去获取AV号
 *
 * @author CY
 */
public class LiveDownloader {

    public static void download(String url) {
        // 获取房间标题
        String playId = BiliBiliUtils.getPlayId(url);
        JsonNode infoByRoom = LiveApi.getInfoByRoom(playId);
        JsonNode roomInfo = infoByRoom.findValue("data").findValue("room_info");
        String roomTitle = FileUtils.getPrettyFileName(roomInfo.findValue("title").asText());
        int liveStatus = roomInfo.findValue("live_status").asInt();
        if (liveStatus == 1) {
            // 获取下载地址
            JsonNode roomPlayInfo = LiveApi.getPlayUrl(playId);
            String liveUrl = roomPlayInfo.findValue("data").findValue("durl").get(0).findValue("url").asText();
            NormalDownloadService.download(liveUrl, new File(SettingsData.path + roomTitle + ".flv"), BiliBiliData.header);
        } else if (liveStatus == 2) {
            // 轮播
            System.err.println("轮播中");
        } else if (liveStatus == 0) {
            System.err.println("未开播");
        }

    }
}
