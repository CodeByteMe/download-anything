package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class CommonApiTest {

    @Test
    void getUserInfo() {
        JsonNode jsonNode = CommonApi.getUserInfo();
        System.out.println(jsonNode);
    }

    @Test
    void getVideoDetail() {
        JsonNode jsonNode = CommonApi.getVideoDetail("3521416");
        System.out.println(jsonNode);
    }

    @Test
    void getVideoTag() {
        JsonNode jsonNode = CommonApi.getVideoTag("3521416");
        System.out.println(jsonNode);
    }

    @Test
    void getVideoDescribe() {
        JsonNode jsonNode = CommonApi.getVideoDescribe("3521416", "1");
        System.out.println(jsonNode);
    }

    @Test
    void getVideoDanmu() {
        String videoDanmu = CommonApi.getVideoDanmu("6041635");
        System.out.println(videoDanmu);
    }

    @Test
    void getUpperInfo() {
        JsonNode jsonNode = CommonApi.getUpperInfo("8047632");
        System.out.println(jsonNode);
    }

    @Test
    void getDefaultSearch() {
        JsonNode jsonNode = CommonApi.getDefaultSearch();
        System.out.println(jsonNode);
    }
}