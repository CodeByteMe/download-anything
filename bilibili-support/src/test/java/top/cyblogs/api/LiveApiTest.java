package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class LiveApiTest {

    @Test
    void getLiveUrlByRoomId() {
        JsonNode jsonNode = LiveApi.getLiveUrlByRoomId("21686237");
        System.out.println(jsonNode);
    }

    @Test
    void getRoomPlayInfo() {
        JsonNode jsonNode = LiveApi.getRoomPlayInfo("21686237");
        System.out.println(jsonNode);
    }

    @Test
    void getLiveUrlByLiveId() {
        JsonNode jsonNode = LiveApi.getLiveUrlByLiveId("21664020");
        System.out.println(jsonNode);
    }

    @Test
    void getLiveUserInfo() {
        JsonNode jsonNode = LiveApi.getLiveUserInfo();
        System.out.println(jsonNode);
    }

    @Test
    void getRoundPlayUrl() {
        JsonNode jsonNode = LiveApi.getRoundPlayUrl("21686237");
        System.out.println(jsonNode);
    }
}