package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getServers方法的返回值的子集结构
 * ===================================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getServers
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cServerChild {

    /**
     * 原始URI
     */
    @JsonProperty("uri")
    private String uri;

    /**
     * 这是当前用于下载的URI。如果涉及重定向，则currentUri和uri可能会不同
     */
    @JsonProperty("currentUri")
    private String currentUri;

    /**
     * 下载速度（字节/秒）
     */
    @JsonProperty("downloadSpeed")
    private long downloadSpeed;
}
