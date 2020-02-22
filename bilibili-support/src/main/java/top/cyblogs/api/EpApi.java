package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.util.JsonUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.BiliBiliUtils;

/**
 * EP的API
 *
 * @author CY
 * @apiNote BiliBili EP API 电影，电视剧，纪录片等
 * 2019年7月15日
 */
public class EpApi {

    private static final String VIDEO_URL = "https://api.bilibili.com/pgc/player/web/playurl?avid=%s&cid=%s&qn=112&type=json&otype=json&ep_id=%s&fnver=0&fnval=16";
    private static final String EP_INITIAL_STATE_URL = "https://www.bilibili.com/bangumi/play/ep%s";
    private static final String EP_INITIAL_STATE_START = "window.__INITIAL_STATE__=";
    private static final String EP_INITIAL_STATE_END = ";(function()";

    /**
     * 获取视频的播放链接
     *
     * @param avid AV号
     * @param cid  弹幕号
     * @return 视频播放链接列表
     */
    public static JsonNode getVideoUrl(String avid, String cid, String epid) {
        String json = BiliBiliUtils.urlText(String.format(VIDEO_URL, avid, cid, epid));
        return JsonUtils.toJsonNode(json);
    }

    /**
     * 得到EP的页面初始化清单列表
     *
     * @param epid Ep的ID
     * @return Ep的初始化列表
     */
    public static JsonNode getEpInitialState(String epid) {
        String html = BiliBiliUtils.urlText(String.format(EP_INITIAL_STATE_URL, epid));
        String json = StringUtils.subString(html, EP_INITIAL_STATE_START, EP_INITIAL_STATE_END);
        return JsonUtils.toJsonNode(json);
    }
}
