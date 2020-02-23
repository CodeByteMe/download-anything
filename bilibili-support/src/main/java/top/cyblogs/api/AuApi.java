package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.util.JsonUtils;
import top.cyblogs.utils.BiliBiliUtils;

/**
 * 歌曲的API
 *
 * @author CY
 * @apiNote BiliBili AU API 音乐
 * 2019年7月15日
 */
public class AuApi {

    private static final String MUSIC_DETAIL_URL = "https://www.bilibili.com/audio/music-service-c/web/song/info?sid=%s";
    private static final String SIMILAR_MUSIC_URL = "https://www.bilibili.com/audio/music-service-c/web/song/similar?sid=%s";
    private static final String MUSIC_URL = "https://www.bilibili.com/audio/music-service-c/web/url?sid=%s&privilege=127&quality=127";
    private static final String UPPER_HOT_MUSIC_URL = "https://www.bilibili.com/audio/music-service-c/web/song/upper-hit?uid=%s&pn=1&ps=6";
    private static final String UPPER_BASE_INFO = "https://www.bilibili.com/audio/music-service-c/web/user/info?uid=%s";

    /**
     * 获取音乐详情（歌词文件的链接在里面）
     *
     * @param sid 歌曲的ID
     * @return 音乐的详情信息
     */
    public static JsonNode getMusicDetail(String sid) {
        String json = BiliBiliUtils.urlText(String.format(MUSIC_DETAIL_URL, sid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取相似音乐
     *
     * @param sid 歌曲的ID
     * @return 相似音乐
     */
    public static JsonNode getSimilarMusic(String sid) {
        String json = BiliBiliUtils.urlText(String.format(SIMILAR_MUSIC_URL, sid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取播放音乐的链接
     *
     * @param sid 歌曲的ID
     * @return 播放音乐链接信息
     */
    public static JsonNode getMusicUrl(String sid) {
        String json = BiliBiliUtils.urlText(String.format(MUSIC_URL, sid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取Upper的热歌列表
     *
     * @param uid Upper的ID
     * @return Upper的热歌列表
     */
    public static JsonNode getUpperHotMusic(String uid) {
        String json = BiliBiliUtils.urlText(String.format(UPPER_HOT_MUSIC_URL, uid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取Upper的基本信息
     *
     * @param uid Upper的ID
     * @return Upper的基本信息，用户名头像签名
     */
    public static JsonNode getUpperBaseInfo(String uid) {
        String json = BiliBiliUtils.urlText(String.format(UPPER_BASE_INFO, uid));
        return JsonUtils.toJsonNode(json);
    }
}
