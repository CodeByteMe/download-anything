package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getVersion方法的返回值
 * ==========================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getVersion
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cVersion {

    @JsonProperty("version")
    private String version;

    @JsonProperty("enabledFeatures")
    private String[] enabledFeatures;
}
