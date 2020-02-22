package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class SsApiTest {

    @Test
    void getSsInitialState() {
        JsonNode jsonNode = SsApi.getSsInitialState("28584");
        System.out.println(jsonNode);
    }
}