package top.cyblogs.api;

import top.cyblogs.util.JsonUtils;
import top.cyblogs.utils.BiliBiliUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * BiliBili的公用Api
 *
 * @author CY
 * 2019年7月15日
 */
public class CommonApi {

    private static final String USER_INFO_URL = "https://api.bilibili.com/x/web-interface/nav";
    private static final String VIDEO_DETAIL_URL = "https://api.bilibili.com/x/web-interface/view/detail?aid=%s";
    private static final String VIDEO_TAG_URL = "https://api.bilibili.com/x/tag/archive/tags?aid=%s";
    private static final String VIDEO_DESCRIBE_URL = "https://api.bilibili.com/x/web-interface/archive/desc?aid=%s&page=%s";
    private static final String VIDEO_DANMU_URL = "https://api.bilibili.com/x/v1/dm/list.so?oid=%s";
    private static final String UPPER_INFO_URL = "https://api.bilibili.com/x/web-interface/card?mid=%s&photo=1";
    private static final String DEFAULT_SEARCH_URL = "https://api.bilibili.com/x/web-interface/search/default";

    /**
     * 获取当前登陆的用户信息
     *
     * @return 用户信息
     */
    public static JsonNode getUserInfo() {
        String json = BiliBiliUtils.urlText(USER_INFO_URL);
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取视频的详细信息
     *
     * @param aid 视频的AV号
     * @return 视频的详细信息
     */
    public static JsonNode getVideoDetail(String aid) {
        String json = BiliBiliUtils.urlText(String.format(VIDEO_DETAIL_URL, aid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取视频的标签
     *
     * @param aid 视频的AV号
     * @return 视频标签
     */
    public static JsonNode getVideoTag(String aid) {
        String json = BiliBiliUtils.urlText(String.format(VIDEO_TAG_URL, aid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取视频的描述信息
     *
     * @param aid  视频的AV号
     * @param page 视频的P
     * @return 视频的描述信息
     */
    public static JsonNode getVideoDescribe(String aid, String page) {
        String json = BiliBiliUtils.urlText(String.format(VIDEO_DESCRIBE_URL, aid, page));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取弹幕XML字符串
     *
     * @param oid 弹幕ID
     */
    public static String getVideoDanmu(String oid) {
        return BiliBiliUtils.urlText(String.format(VIDEO_DANMU_URL, oid));
    }

    /**
     * Upper的个人信息
     *
     * @param mid Upper的Id
     */
    public static JsonNode getUpperInfo(String mid) {
        String json = BiliBiliUtils.urlText(String.format(UPPER_INFO_URL, mid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 获取B站今日的默认搜索
     *
     * @return 今日搜索框
     */
    public static JsonNode getDefaultSearch() {
        String json = BiliBiliUtils.urlText(DEFAULT_SEARCH_URL);
        return JsonUtils.toJsonNode(json);
    }
}
