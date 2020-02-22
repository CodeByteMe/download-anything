package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.util.JsonUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.BiliBiliUtils;

/**
 * 直播相关的API
 *
 * @author CY
 * @apiNote BiliBili直播API
 * 2019年7月15日
 */
public class LiveApi {

    private static final String LIVE_URL_BY_ROOM_ID_URL = "https://api.live.bilibili.com/room/v1/Room/playUrl?cid=%s&qn=10000&platform=web";
    private static final String ROOM_PLAY_INFO_URL = "https://api.live.bilibili.com/xlive/web-room/v1/index/getRoomPlayInfo?room_id=%s&play_url=1&mask=1&qn=10000&platform=web";
    private static final String LIVE_URL_BY_LIVE_ID_URL = "https://live.bilibili.com/%s";
    private static final String LIVE_URL_BY_LIVE_ID_START = "<script>window.__NEPTUNE_IS_MY_WAIFU__=";
    private static final String LIVE_URL_BY_LIVE_ID_END = "</script>";
    private static final String LIVE_USER_INFO_URL = "https://api.live.bilibili.com/xlive/web-ucenter/user/get_user_info";
    private static final String ROUND_PLAY_URL = "https://api.live.bilibili.com/live/getRoundPlayVideo?room_id=%s";
    private static final String INFO_BY_ROOM = "https://api.live.bilibili.com/xlive/web-room/v1/index/getInfoByRoom?room_id=%s";
    private static final String PLAY_URL = "https://api.live.bilibili.com/room/v1/Room/playUrl?cid=%s&qn=1000&platform=web";

    public static JsonNode getPlayUrl(String roomId) {
        String json = BiliBiliUtils.urlText(String.format(PLAY_URL, roomId));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取直播的地址，直播是不可能得知下载进度的
     *
     * @param cid 直播间所对应的room_id
     * @return 直播信息
     */
    public static JsonNode getLiveUrlByRoomId(String cid) {
        String json = BiliBiliUtils.urlText(String.format(LIVE_URL_BY_ROOM_ID_URL, cid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取房间的播放信息
     *
     * @param roomId 房间ID
     * @return 房间播放信息
     */
    public static JsonNode getRoomPlayInfo(String roomId) {
        String json = BiliBiliUtils.urlText(String.format(ROOM_PLAY_INFO_URL, roomId));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 通过用户给的链接中的ID，获取直播视频的链接
     *
     * @param liveId 直播ID
     * @return 直播信息
     */
    public static JsonNode getLiveUrlByLiveId(String liveId) {
        String html = BiliBiliUtils.urlText(String.format(LIVE_URL_BY_LIVE_ID_URL, liveId));
        String json = StringUtils.subString(html, LIVE_URL_BY_LIVE_ID_START, LIVE_URL_BY_LIVE_ID_END);
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取直播状态下用户的信息
     *
     * @return 用户直播状态下的信息
     */
    public static JsonNode getLiveUserInfo() {
        String json = BiliBiliUtils.urlText(LIVE_USER_INFO_URL);
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取轮播的URL
     */
    public static JsonNode getRoundPlayUrl(String roomId) {
        String json = BiliBiliUtils.urlText(String.format(ROUND_PLAY_URL, roomId));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取直播间信息
     *
     * @param roomId 直播间ID
     */
    public static JsonNode getInfoByRoom(String roomId) {
        String json = BiliBiliUtils.urlText(String.format(INFO_BY_ROOM, roomId));
        return JsonUtils.toJsonNode(json);
    }
}
