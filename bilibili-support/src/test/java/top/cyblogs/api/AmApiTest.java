package top.cyblogs.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class AmApiTest {

    @Test
    void getAlbumInfo() {
        JsonNode jsonNode = AmApi.getAlbumInfo("29784654");
        System.out.println(jsonNode);
    }

    @Test
    void getAlbumList() {
        JsonNode jsonNode = AmApi.getAlbumList("29784654");
        System.out.println(jsonNode);
    }

    @Test
    void getHotAlbumList() {
        JsonNode jsonNode = AmApi.getHotAlbumList();
        System.out.println(jsonNode);
    }
}