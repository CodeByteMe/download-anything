package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.util.JsonUtils;
import top.cyblogs.utils.BiliBiliUtils;

/**
 * 小视频API
 *
 * @author CY
 * @apiNote vc.bilibili.com站点的小视频API
 * 2019年7月15日
 */
public class VcApi {

    private static final String PLAY_DETAIL_URL = "http://api.vc.bilibili.com/clip/v1/video/detail?video_id=%s&need_playurl=1";
    private static final String RECOMMEND_URL = "http://api.vc.bilibili.com/clip/v1/video/index?platform=pc&page_size=9&need_playurl=0";

    /**
     * 获取小视频的播放详情（包含播放地址）
     *
     * @param videoId 视频Id
     * @return 播放详情
     */
    public static JsonNode getPlayDetail(String videoId) {
        String json = BiliBiliUtils.urlText(String.format(PLAY_DETAIL_URL, videoId));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取小视频精彩推荐
     *
     * @return 推荐列表
     */
    public static JsonNode getRecommendUrl() {
        String json = BiliBiliUtils.urlText(RECOMMEND_URL);
        return JsonUtils.toJsonNode(json);
    }
}
