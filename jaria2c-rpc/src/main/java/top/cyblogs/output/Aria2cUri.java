package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getUris方法的返回值
 * =======================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getUris
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cUri {

    /**
     * URI
     */
    @JsonProperty("uri")
    private String uri;

    /**
     * 如果已使用URI，则为“used”。
     * 如果URI仍在队列中等待，则“waiting”。
     */
    @JsonProperty("status")
    private String status;
}