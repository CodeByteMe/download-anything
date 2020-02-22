package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getSessionInfo方法的返回值
 * ==============================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getSessionInfo
 */
@Data
public class Aria2cSession {

    /**
     * 每次调用aria2时生成的会话ID
     */
    @JsonProperty("sessionId")
    private String sessionId;
}
