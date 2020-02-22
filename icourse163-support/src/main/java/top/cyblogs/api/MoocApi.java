package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import top.cyblogs.data.SessionData;
import top.cyblogs.util.JsonUtils;
import top.cyblogs.utils.MoocUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 慕课大学的API
 */
public class MoocApi {

    private static List<Map<String, Object>> getCourseList(String courseUrl) throws IOException {
        URL url = new URL(courseUrl);
        String query = url.getQuery();
        Matcher matcher = Pattern.compile("tid=(\\d*)").matcher(query);
        List<Map<String, Object>> maps = null;
        if (matcher.find()) {
            String tid = matcher.group(1).trim();
            System.out.println("[" + tid + "]");
            String courseDwr = MoocApi.getCourseDwr(tid);
            System.out.println(courseDwr);
            maps = MoocUtils.parseDwr(courseDwr, new String[]{"id", "chapterId", "contentId", "contentType", "name", "lessonId", "termId"});
        }
        return maps;
    }

    private static String getCourseDwr(String tid) throws IOException {
        Connection connect = Jsoup.connect("https://www.icourse163.org/dwr/call/plaincall/CourseBean.getLastLearnedMocTermDto.dwr").ignoreContentType(true);

        connect.header("content-type", "text/plain");
        connect.header("referer", "https://www.icourse163.org/");
        connect.header("cookie", SessionData.cookie);

        connect.data("callCount", "1");
        connect.data("scriptSessionId", "${scriptSessionId}190");
        connect.data("httpSessionId", SessionData.csrfKey);
        connect.data("c0-scriptName", "CourseBean");
        connect.data("c0-methodName", "getLastLearnedMocTermDto");
        connect.data("c0-id", "0");
        connect.data("c0-param0", "number:" + tid);
        connect.data("batchId", String.valueOf(System.currentTimeMillis()));
        return connect.post().text();
    }

    public static JsonNode getHdVideoUrl(String signature, String videoId) throws IOException {
        String videoResourceUrl = String.format("https://vod.study.163.com/eds/api/v1/vod/video?videoId=%s&signature=%s&clientType=1", videoId, signature);
        Connection videoUrl = Jsoup.connect(videoResourceUrl).ignoreContentType(true);
        String json = videoUrl.get().text();
        JsonNode videoJson = JsonUtils.toJsonNode(json);
        JsonNode videos = videoJson.findValue("videos");
        return videos.get(videos.size() - 1);

    }

    public static JsonNode getVideoResourceUrl(Long bizId) throws IOException {
        Connection connect = Jsoup.connect(String.format("https://www.icourse163.org/web/j/resourceRpcBean.getResourceToken.rpc?csrfKey=%s", SessionData.csrfKey)).ignoreContentType(true);
        connect.header("cookie", SessionData.cookie);
        connect.data("bizId", String.valueOf(bizId));
        connect.data("bizType", "1");
        connect.data("contentType", "1");

        String text = connect.post().text();
        JsonNode jsonNode = JsonUtils.toJsonNode(text);
        System.out.println(jsonNode);
        return jsonNode.findValue("videoSignDto");
    }

}
