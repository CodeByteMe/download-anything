package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class VcApiTest {

    @Test
    void getPlayDetail() {
        JsonNode jsonNode = VcApi.getPlayDetail("2860211");
        System.out.println(jsonNode);
    }

    @Test
    void getRecommendUrl() {
        JsonNode jsonNode = VcApi.getRecommendUrl();
        System.out.println(jsonNode);
    }
}