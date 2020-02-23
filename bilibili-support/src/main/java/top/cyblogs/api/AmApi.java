package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.util.JsonUtils;
import top.cyblogs.utils.BiliBiliUtils;

/**
 * 歌曲专辑的API
 *
 * @author CY
 * @apiNote BiliBili AM API 音频专辑
 * <p>
 * 2019年7月15日
 */
public class AmApi {

    private static final String ALBUM_INFO_URL = "https://www.bilibili.com/audio/music-service-c/web/menu/info?sid=%s";
    private static final String ALBUM_LIST_URL = "https://www.bilibili.com/audio/music-service-c/web/song/of-menu?sid=%s&pn=1&ps=1000000";
    private static final String HOT_ALBUM_LIST_URL = "https://www.bilibili.com/audio/music-service-c/web/menu/hit?pn=1&ps=1000000";

    /**
     * 获取专辑信息
     *
     * @param sid 专辑ID
     * @return 专辑信息
     */
    public static JsonNode getAlbumInfo(String sid) {
        String json = BiliBiliUtils.urlText(String.format(ALBUM_INFO_URL, sid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取专辑列表
     *
     * @param sid 专辑ID
     * @return 专辑列表
     */
    public static JsonNode getAlbumList(String sid) {
        String json = BiliBiliUtils.urlText(String.format(ALBUM_LIST_URL, sid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取36个热门歌单
     */
    public static JsonNode getHotAlbumList() {
        String json = BiliBiliUtils.urlText(HOT_ALBUM_LIST_URL);
        return JsonUtils.toJsonNode(json);
    }
}
