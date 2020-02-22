package top.cyblogs.api;

import top.cyblogs.util.JsonUtils;
import top.cyblogs.util.StringUtils;
import top.cyblogs.utils.BiliBiliUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * SS的API
 *
 * @author CY
 * @apiNote BiliBili SS API 番剧等
 * 2019年7月15日
 */
public class SsApi {

    private static final String SS_INITIAL_STATE_URL = "https://www.bilibili.com/bangumi/play/ss%s";
    private static final String SS_INITIAL_STATE_START = "window.__INITIAL_STATE__=";
    private static final String SS_INITIAL_STATE_END = ";(function()";

    /**
     * 获取初始化清单
     * 获取了该初始化清单之后需要使用EpApi类中的获取播放地址来执行下一步操作
     *
     * @param ssid ss的id
     * @return 初始化清单
     */
    public static JsonNode getSsInitialState(String ssid) {
        String html = BiliBiliUtils.urlText(String.format(SS_INITIAL_STATE_URL, ssid));
        String json = StringUtils.subString(html, SS_INITIAL_STATE_START, SS_INITIAL_STATE_END);
        return JsonUtils.toJsonNode(json);
    }
}
