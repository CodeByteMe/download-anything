package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class AuApiTest {

    @Test
    void getMusicDetail() {
        JsonNode jsonNode = AuApi.getMusicDetail("305581");
        System.out.println(jsonNode);
    }

    @Test
    void getSimilarMusic() {
        JsonNode jsonNode = AuApi.getSimilarMusic("305581");
        System.out.println(jsonNode);
    }

    @Test
    void getMusicUrl() {
        JsonNode jsonNode = AuApi.getMusicUrl("305581");
        System.out.println(jsonNode);
    }

    @Test
    void getUpperHotMusic() {
        JsonNode jsonNode = AuApi.getUpperHotMusic("44905145");
        System.out.println(jsonNode);
    }

    @Test
    void getUpperBaseInfo() {
        JsonNode jsonNode = AuApi.getUpperBaseInfo("44905145");
        System.out.println(jsonNode);
    }
}