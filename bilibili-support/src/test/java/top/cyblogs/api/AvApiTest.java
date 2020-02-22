package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class AvApiTest {

    @Test
    void getVideoUrl() {
        JsonNode videoUrl = AvApi.getVideoUrl("3521416", "6041635");
        System.out.println(videoUrl);
    }
}