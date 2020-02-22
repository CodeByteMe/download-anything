package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class EpApiTest {

    @Test
    void getVideoUrl() {
        JsonNode jsonNode = EpApi.getVideoUrl("30818651", "53869138", "246674");
        System.out.println(jsonNode);
    }

    @Test
    void getEpInitialState() {
        JsonNode jsonNode = EpApi.getEpInitialState("246674");
        System.out.println(jsonNode);
    }
}